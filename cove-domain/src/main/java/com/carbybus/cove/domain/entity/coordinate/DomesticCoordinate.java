package com.carbybus.cove.domain.entity.coordinate;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 国内坐标
 * 前台显示
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "init")
public class DomesticCoordinate extends Coordinate {

}