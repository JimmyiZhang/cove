package com.carbybus.cove.domain.view;

import lombok.AllArgsConstructor;
import lombok.Data;


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
    private String level;
}
