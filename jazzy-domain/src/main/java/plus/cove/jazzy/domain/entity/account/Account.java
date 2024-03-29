package plus.cove.jazzy.domain.entity.account;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import plus.cove.infrastructure.component.impl.DefaultEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDateTime;

/**
 * 账户信息
 *
 * @author jimmy.zhang
 * @date 2019-05-20
 */
@Data
@Entity
@Table(name = "account")
@EqualsAndHashCode(callSuper = true)
public class Account extends DefaultEntity {
    private String name;
    private String secret;
    private String salt;
    private UserStatus status;
    private LocalDateTime createTime;
    @Transient
    private LocalDateTime expiredTime;

    /**
     * 验证密码
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-05-20
     */
    public boolean checkPassword(String password) {
        String pass = generatePassword(password, this.getSalt());
        return pass.equals(this.getSecret());
    }

    /**
     * 更新过期时间
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @since 1.0
     */
    public void active() {
        this.status = UserStatus.ACTIVE;
        this.expiredTime = LocalDateTime.of(2099, 12, 31, 0, 0, 0);
    }

    /**
     * 是否有效
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-06-25
     */
    public boolean checkStatus() {
        return this.status == UserStatus.ACTIVE &&
                this.expiredTime.isAfter(LocalDateTime.now());
    }

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
        Account entity = new Account();

        String salt = RandomUtil.randomString(32);
        String pass = generatePassword(password, salt);

        LocalDateTime createTime = LocalDateTime.now();
        LocalDateTime expiredTime = createTime.plusDays(7L);
        entity.createTime = createTime;
        entity.expiredTime = expiredTime;
        entity.name = name;
        entity.status = UserStatus.NONE;
        entity.salt = salt;
        entity.secret = pass;

        return entity;
    }

    /**
     * 生成密码
     *
     * @param password 原密码
     * @param salt     加盐
     * @return
     */
    private static String generatePassword(String password, String salt) {
        return DigestUtil.sha256Hex(password + salt);
    }
}
