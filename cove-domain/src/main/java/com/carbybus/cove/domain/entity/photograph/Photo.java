package com.carbybus.cove.domain.entity.photograph;

import com.baomidou.mybatisplus.annotation.TableName;
import com.carbybus.cove.domain.entity.coordinate.Coordinate;
import com.carbybus.cove.domain.principal.UserPrincipal;
import com.carbybus.infrastructure.component.impl.DefaultEntity;
import com.carbybus.infrastructure.utils.StringConstants;
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
@EqualsAndHashCode(callSuper = true)
@TableName(value = "photo")
public class Photo extends DefaultEntity {
    private String tag;
    private String url;
    private LocalDateTime takeTime;
    private LocalDateTime createTime;
    private Coordinate location;
    private String description;
    private Long ownerId;

    /**
     * 创建
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-05-20
     */
    public static Photo create(UserPrincipal user) {
        Photo photo = new Photo();
        photo.valueOf();

        photo.setCreateTime(LocalDateTime.now())
                .setOwnerId(user.getUserId())
                .setTag(StringConstants.EMPTY)
                .setDescription(StringConstants.EMPTY);
        return photo;
    }
}
