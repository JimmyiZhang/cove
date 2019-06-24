package com.carbybus.cove.domain.entity.user;

import com.baomidou.mybatisplus.annotation.TableName;
import com.carbybus.infrastructure.component.impl.DefaultEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 用户激活
 *
 * @author jimmy.zhang
 * @date 2019-06-24
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "account_activation")
public class AccountActivation extends DefaultEntity {
    private Long userId;
    private String userCode;
    private LocalDateTime createTime;
    private LocalDateTime expiredTime;

    /** 
    * 创建用户激活实体 
    * @param  
    * @return  
    * @author jimmy.zhang 
    * @date 2019-06-24 
    */ 
    public static AccountActivation create(Long userId){
        AccountActivation ua = new AccountActivation();
        ua.valueOf();

        LocalDateTime createTime = LocalDateTime.now();
        // 默认7天
        LocalDateTime expiredTime = createTime.plusDays(7);

        // 激活码-保证全局唯一
        String code = UUID.randomUUID().toString();

        ua.setCreateTime(createTime)
                .setExpiredTime(expiredTime)
                .setUserId(userId)
                .setUserCode(code);

        return ua;
    }
}
