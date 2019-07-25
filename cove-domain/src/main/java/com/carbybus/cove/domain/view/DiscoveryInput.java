package com.carbybus.cove.domain.view;

import com.carbybus.cove.domain.entity.discovery.DiscoveryLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 探索输入
 *
 * 包括游戏难度
 *
 * @author jimmy.zhang
 * @date 2019-07-24
 */
@Data
@AllArgsConstructor
public class DiscoveryInput {
    @NotNull(message = "游戏难度不能为空")
    private DiscoveryLevel level;

    private Double latitude;
    private Double longitude;
}
