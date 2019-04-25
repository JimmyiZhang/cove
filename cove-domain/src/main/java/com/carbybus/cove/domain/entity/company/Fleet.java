package com.carbybus.cove.domain.entity.company;

/**
 * 车队信息
 */

import com.baomidou.mybatisplus.annotation.TableName;
import com.carbybus.infrastructure.component.impl.DefaultEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 公司信息
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "init")
@TableName(value = "fleet")
public class Fleet extends DefaultEntity {
    /**
     * 名称
     */
    private String name;

    /**
     * 队长编码
     */
    private long leaderId;

    /**
     * 公司编码
     */
    private long companyId;

}