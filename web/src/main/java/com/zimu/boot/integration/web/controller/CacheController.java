package com.zimu.boot.integration.web.controller;

import com.zimu.boot.integration.service.CacheService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cache")
@Api("缓存接口")
public class CacheController {

    private CacheService cacheService;

    public CacheController(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ApiOperation(value = "获取缓存", notes="获取缓存")
    public Object get(String key) {
        return cacheService.gey(key);
    }

    @RequestMapping(value = "/put", method = RequestMethod.PUT)
    @ApiOperation(value = "设置缓存", notes="设置缓存")
    public void put(String key, String value) {
        cacheService.put(key, value);
    }
}
