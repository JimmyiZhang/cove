package com.carbybus.cove.domain.entity.photograph;

import com.baomidou.mybatisplus.annotation.TableName;
import com.carbybus.cove.domain.entity.coordinate.Coordinate;
import com.carbybus.infrastructure.component.impl.DefaultEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 照片
 *
 * @author jimmy.zhang
 * @date 2019-05-20
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName(value = "photo")
public class Photo extends DefaultEntity {
    private LocalDateTime takeTime;
    private LocalDateTime uploadTime;
    private Coordinate location;
    private String description;
    private String tags;

    private Long ownerId;
}
