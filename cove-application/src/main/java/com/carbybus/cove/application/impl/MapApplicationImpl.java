package com.carbybus.cove.application.impl;

import com.carbybus.cove.application.MapApplication;
import com.carbybus.cove.repository.CityRepository;
import com.carbybus.cove.repository.TravellerRepository;
import com.carbybus.infrastructure.caching.CacheUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 地图应用
 *
 * @author jimmy.zhang
 * @date 2019-04-19
 */
@Service
@Slf4j
public class MapApplicationImpl implements MapApplication {
    @Autowired
    private CityRepository cityRep;
    @Autowired
    private CacheUtils cacheUtils;


}
