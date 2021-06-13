package com.yzy;

import com.yzy.entity.MapData;
import com.yzy.entity.Result;
import com.yzy.entity.vo.MapDataVO;
import com.yzy.entity.vo.ProvinceVO;
import com.yzy.mapper.ProvinceMapper;
import com.yzy.service.AreaService;
import com.yzy.service.MapDataService;
import com.yzy.service.ProvinceService;
import com.yzy.util.MapDataUtil;
import com.yzy.util.SFTPUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class HealthyMsApplicationTests {
    @Autowired
    MapDataService mapDataService;

    @Autowired
    MapDataUtil util;
    
    @Autowired
    AreaService areaService;

    @Autowired
    SFTPUtils sftpUtils;

    @Test
    public void contextLoads() {
        sftpUtils.Login();


    }

}
class TestClass{
    int count;
    String name;

    public TestClass(int count, String name) {
        this.count = count;
        this.name = name;
    }
}
