package com.carbybus.cove.domain.entity.coordinate;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 标准坐标
 * 用来计算里程
 *
 * @author Jimmy.Zhang
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "init")
public class StandardCoordinate extends Coordinate {

}