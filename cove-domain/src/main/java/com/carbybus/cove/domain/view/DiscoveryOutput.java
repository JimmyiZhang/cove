package com.carbybus.cove.domain.view;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * 探索输出
 *
 * 根据游戏难度获取目标点的坐标
 *
 * @author jimmy.zhang
 * @date 2019-07-24
 */
@Data
@AllArgsConstructor
public class DiscoveryOutput {
    private Double latitude;
    private Double longitude;
}
