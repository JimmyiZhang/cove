package com.carbybus.cove.domain.entity.city;

import com.baomidou.mybatisplus.annotation.TableName;
import com.carbybus.cove.domain.entity.coordinate.Coordinate;
import com.carbybus.infrastructure.component.impl.DefaultEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 城市
 * 包括城市名称，城市中心坐标
 *
 * @author jimmy.zhang
 * @date 2019-07-24
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "city")
public class City extends DefaultEntity {
    private String name;
    private String title;
    private Coordinate location;
    private CityType type;
}
