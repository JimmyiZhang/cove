package com.carbybus.cove.domain.entity.user;

import com.baomidou.mybatisplus.annotation.TableName;
import com.carbybus.infrastructure.component.impl.DefaultEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDateTime;

/**
 * 账户信息
 *
 * @author jimmy.zhang
 * @date 2019-05-20
 */

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@TableName(value = "account")
public class Account extends DefaultEntity {
    private String name;
    private String secret;
    private String salt;
    private UserStatus status;
    private LocalDateTime createTime;

    /**
     * 创建
     *
     * @param name     用户名
     * @param password 用户密码
     * @return
     * @author jimmy.zhang
     * @date 2019-05-20
     */
    public static Account create(String name, String password) {
        Account account = new Account();
        account.valueOf();

        String salt = RandomStringUtils.randomAlphanumeric(32);
        String pass = generatePassword(password, salt);
        account.setCreateTime(LocalDateTime.now())
                .setName(name)
                .setStatus(UserStatus.UNACTIVED)
                .setSalt(salt)
                .setSecret(pass);

        return account;
    }

    /**
     * 验证密码
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-05-20
     */
    public boolean validate(String password) {
        String pass = generatePassword(password, this.getSalt());
        return pass.equals(this.getSecret());
    }

    /**
     * 生成密码
     *
     * @param password 原密码
     * @param salt     加盐
     * @return
     */
    private static String generatePassword(String password, String salt) {
        return DigestUtils.sha256Hex(password + salt);
    }
}
