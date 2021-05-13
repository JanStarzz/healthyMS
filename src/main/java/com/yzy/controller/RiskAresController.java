package com.yzy.controller;

import com.yzy.entity.Result;
import com.yzy.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author LuBaby
 * @date 2021/5/12 14:06
 */
@RestController
@RequestMapping("/area")
public class RiskAresController {
    @Autowired
    AreaService areaService;

    @GetMapping("/area/{status}")
    public Result getRiskAreas(@PathVariable Integer status){
        return areaService.getRiskAreaByStatus(status);
    }

    @PutMapping("/area")
    public Result setRiskAreas(@RequestBody Map map){
        return areaService.setRiskArea(map);
    }
}
