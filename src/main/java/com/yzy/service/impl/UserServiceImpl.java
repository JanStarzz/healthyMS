package com.yzy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yzy.entity.*;
import com.yzy.entity.vo.UserInfoVO;
import com.yzy.enumentity.HealthyCodeColor;
import com.yzy.enumentity.RiskEnum;
import com.yzy.mapper.UserAreaMapper;
import com.yzy.mapper.UserCurrentMapper;
import com.yzy.mapper.UserMapper;
import com.yzy.service.AreaService;
import com.yzy.service.UserService;
import com.yzy.util.QrCodeUtil;
import com.yzy.util.ResultUtil;
import com.yzy.util.SFTPUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author LuBaby
 * @date 2021/5/13 15:50
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    AreaService areaService;

    @Autowired
    UserCurrentMapper userCurrentMapper;

    @Autowired
    UserAreaMapper userAreaMapper;

    @Autowired
    SFTPUtils sftpUtils;

    @Override
    public Result saveUserCurrent(UserInfoVO userInfoVO) {
        Integer code = areaService.areaUtil(userInfoVO.getCity(), userInfoVO.getCounty());
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");// a为am/pm的标记
        Date date = new Date();
        User user = User.builder()
                .userId(userInfoVO.getUserId())
                .idNumber(userInfoVO.getIdCard())
                .phone(userInfoVO.getPhone())
                .realName(userInfoVO.getRealName())
                .build();

        User byId = getById(user.getUserId());
        boolean userNoInit = (byId.getRealName() == null);
        if (userNoInit || (byId.getRealName().equals(user.getRealName()) && byId.getIdNumber().equals(user.getIdNumber()))
        ) {
            updateById(user);
        } else {
            return ResultUtil.error(304, "不支持修改姓名和身份证号");
        }


        UserCurrent userCurrent = UserCurrent.builder()
                .userId(userInfoVO.getUserId())
                .areaCode(code)
                .bodyHeat(userInfoVO.getBodyHeat())
                .detailAddr(userInfoVO.getDetailAddr())
                .symptom(userInfoVO.getIsSymptom())
                .contactPatient(userInfoVO.getContactPatient())
                .atForeign(userInfoVO.getForeign())
                .risk(userInfoVO.getRisk())
                .createTime(sdf.format(date))
                .build();

        if (userCurrentMapper.selectById(userCurrent.getUserId()) == null) {
            userCurrentMapper.insert(userCurrent);
        } else {
            userCurrentMapper.updateById(userCurrent);
        }


        return ResultUtil.success();
    }


    @Override
    public Result gerQRCode(String userId) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");// a为am/pm的标记
        Date date = new Date();// 获取当前时间
        BufferedImage bufferedImage;
        Integer healthyCodeColor = HealthyCodeColor.GREEN.getCode();
        String context;
        String fileName = userId + System.currentTimeMillis() + ".png";
        sftpUtils.Login();
        UserCurrent userCurrent = userCurrentMapper.selectById(userId);
        User user = getById(userId);
        userCurrent.setCreateTime(sdf.format(date));

        context = "姓名：" + user.getRealName() + "\n"
                + "身份证号码：" + user.getIdNumber() + "\n"
                + "刷新时间：" + sdf.format(date) + "\n"
        ;
        QueryWrapper<UserArea> userAreaQueryWrapper = new QueryWrapper<>();
        userAreaQueryWrapper.eq("user_id", userId);
        List<UserArea> userAreas = userAreaMapper.selectList(userAreaQueryWrapper);
        //15天内是否经过高风险地区
        for (UserArea u : userAreas) {
            Area status = areaService.getRiskStatusByAreaCode(u.getAreaCode());
            healthyCodeColor = healthyCodeColor > status.getStatus() ? healthyCodeColor : status.getStatus();
            if (healthyCodeColor == HealthyCodeColor.RED.getCode()) {
                context = "15天内经过高风险地区：" + status.getName() + "\n";
                //TODO
                //获取红码并写入信息
                break;
            }
        }
        //是否经过高风险地区
        boolean isThrough = userCurrent.getRisk() == RiskEnum.HIGH.getStatus();
        if (isThrough) {
            healthyCodeColor = HealthyCodeColor.RED.getCode();
            context = context + "经过高风险地区\n";
        }
        //是否有异常症状
        boolean isSymptom = userCurrent.getSymptom();
        if (isSymptom) {
            healthyCodeColor = HealthyCodeColor.RED.getCode();
            context = context + "有异常状况\n";
        }
        //是否和新冠疑似、确诊病人接触过
        boolean contactPatient = userCurrent.getContactPatient();
        if (contactPatient) {
            healthyCodeColor = HealthyCodeColor.RED.getCode();
            context = context + "和新冠疑似、确诊病人接触过\n";
        }
        //居住地是否是高风险地区
        boolean isRiskArea = areaService.getRiskStatusByAreaCode(userCurrent.getAreaCode()).getStatus() == RiskEnum.HIGH.getStatus();
        if (isRiskArea) {
            healthyCodeColor = HealthyCodeColor.RED.getCode();
            context = context + "居住地是高风险地区\n";
        }

        if (healthyCodeColor == HealthyCodeColor.RED.getCode()) {
            bufferedImage = QrCodeUtil.buildRedQuickMarkImage(context);
            InputStream imageStream = QrCodeUtil.getImageStream(bufferedImage);
            //上传二维码
            sftpUtils.upload(fileName, imageStream);
            userCurrent.setHealthyCode(fileName);
            userCurrentMapper.updateById(userCurrent);
            return ResultUtil.success(userCurrent);
        }

        //是否高体温
        boolean isHighHeart = userCurrent.getBodyHeat() > 37.3;
        if (isHighHeart) {
            healthyCodeColor = HealthyCodeColor.ORANGE.getCode();
            context = context + "体温高于正常温度：" + userCurrent.getBodyHeat() + "°\n";
        }
        //是否中风险地区
        boolean isMidRiskArea = areaService.getRiskStatusByAreaCode(userCurrent.getAreaCode()).getStatus() == RiskEnum.MID.getStatus();
        if (isMidRiskArea) {
            healthyCodeColor = HealthyCodeColor.ORANGE.getCode();
            context = context + "处于中风险地区：" + areaService.getRiskStatusByAreaCode(userCurrent.getAreaCode()).getName() + "\n";
        }

        if (healthyCodeColor == HealthyCodeColor.ORANGE.getCode()
        ) {
            bufferedImage = QrCodeUtil.buildYellowQuickMarkImage(context);
            InputStream imageStream = QrCodeUtil.getImageStream(bufferedImage);

            //上传二维码
            sftpUtils.upload(fileName, imageStream);

            userCurrent.setHealthyCode(fileName);
            userCurrentMapper.updateById(userCurrent);
            return ResultUtil.success(userCurrent);
        }

        bufferedImage = QrCodeUtil.buildBlueQuickMarkImage(context);
        InputStream imageStream = QrCodeUtil.getImageStream(bufferedImage);
        //上传二维码
        sftpUtils.upload(fileName, imageStream);
        sftpUtils.logout();
        userCurrent.setHealthyCode(fileName);
        userCurrentMapper.updateById(userCurrent);
        return ResultUtil.success(userCurrent);
    }

    @Override
    public Result getUserCurrent(String userId) {
        UserCurrent userCurrent = userCurrentMapper.selectById(userId);
        User user = getById(userId);
        UserInfoVO build = UserInfoVO.builder()
                .realName(user.getRealName())
                .idCard(user.getIdNumber())
                .phone(user.getPhone())
                .healthyCode(userCurrent.getHealthyCode())
                .createTime(userCurrent.getCreateTime())
                .build();

        return ResultUtil.success(build);
    }
}
