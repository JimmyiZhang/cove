package com.carbybus.cove.domain.entity.company;

import com.baomidou.mybatisplus.annotation.TableName;
import com.carbybus.infrastructure.component.impl.DefaultEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 账户
 * 登录信息
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "init")
@EqualsAndHashCode(callSuper = false)
@TableName(value = "account")
public class Account extends DefaultEntity {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 加盐
     */
    private String salt;

    /**
     * 过期时间
     */
    private LocalDateTime expiredTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-04-19
     */
    public static Account create() {
        LocalDateTime expiredTime = LocalDateTime.of(2099, 12, 31, 23, 59);

        Account account = new Account();
        account.valueOf();

        account.setExpiredTime(expiredTime)
                .setCreateTime(LocalDateTime.now());
        return account;
    }
}