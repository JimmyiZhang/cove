package com.carbybus.cove.domain.entity.user;

import com.baomidou.mybatisplus.annotation.TableName;
import com.carbybus.infrastructure.component.impl.DefaultEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 用户信息
 *
 * @author jimmy.zhang
 * @date 2019-05-20
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "traveller")
public class Traveller extends DefaultEntity {
    private String name;
    private String avatar;
    private String signature;
    private UserGender gender;
    private LocalDateTime createTime;

    /**
     * 创建
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-05-20
     */
    public static Traveller create(String name) {
        Traveller traveller = new Traveller();
        traveller.valueOf();
        traveller.setCreateTime(LocalDateTime.now())
                .setName(name);

        return traveller;
    }
}
