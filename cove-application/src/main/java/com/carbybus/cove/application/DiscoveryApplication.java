package com.carbybus.cove.application;


import com.carbybus.cove.domain.entity.discovery.DiscoveryPlace;
import com.carbybus.cove.domain.view.DiscoveryInput;

/**
 * 地图应用
 *
 * @author jimmy.zhang
 * @date 2019-03-28
 */
public interface DiscoveryApplication {
    /**
     * 根据输入生成探索点
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-07-25
     */
    DiscoveryPlace generatePlace(DiscoveryInput input);
}
