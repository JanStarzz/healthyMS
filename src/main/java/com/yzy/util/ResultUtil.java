package com.yzy.util;

import com.yzy.entity.Result;
import com.yzy.enumentity.ResultCode;

/**
 * Result返回类
 * @author LuBaby
 * @date 2021/5/11 17:32
 */
public class ResultUtil {
    /**
     * @param @param  object
     * @param @return 设定文件
     * @return Result 返回类型
     * @Title: success
     * @Description: 成功有返回
     */
    public static Result success(Object object) {
        Result Result = new Result(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(),object);

        return Result;
    }

    /**
     * @param @return 设定文件
     * @return Result 返回类型
     * @Title: success
     * @Description: 成功无返回
     */
    public static Result success() {
        return ResultUtil.success(null);
    }


    /**
     * @return Result 返回类型
     * @Title: paramError
     * @Description: 数据库错误
     */
    public static Result dataBaseError() {
        return new Result(ResultCode.DATABASE_ERROR.getCode(), ResultCode.DATABASE_ERROR.getMessage());
    }

    /**
     * @param @param  code
     * @param @param  msg
     * @param @return 设定文件
     * @return Result 返回类型, 错误返回时状态码为 9999
     * @Title: error
     * @Description: 失败方法返回
     */
    public static Result error(int code, String msg) {
        Result Result = new Result(code, msg);
        return Result;
    }

    /**
     * 未知错误
     * @return
     */
    public static Result unknownError(){
        return new Result(ResultCode.UNKNOWN_ERROR.getCode() ,ResultCode.UNKNOWN_ERROR.getMessage());
    }

    public static Result paramError(){
        return Result.builder().code(ResultCode.PARAM_ERROR.getCode()).message(ResultCode.PARAM_ERROR.getMessage()).build();
    }

    public static Result firstLogin(String s){
        return Result.builder().code(ResultCode.First_LOGIN.getCode()).message(ResultCode.First_LOGIN.getMessage()).data(s).build();
    }
}
