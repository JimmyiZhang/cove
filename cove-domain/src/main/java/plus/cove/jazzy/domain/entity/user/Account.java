package plus.cove.jazzy.domain.entity.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import plus.cove.infrastructure.component.impl.DefaultEntity;

import javax.persistence.Entity;
import java.time.LocalDateTime;

/**
 * 账户信息
 *
 * @author jimmy.zhang
 * @date 2019-05-20
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Account extends DefaultEntity {
    private String name;
    private String secret;
    private String salt;
    private UserStatus status;
    private LocalDateTime createTime;
    private LocalDateTime expiredTime;

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
        entity.valueOf();

        String salt = RandomStringUtils.randomAlphanumeric(32);
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
     * 是否有效
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-06-25
     */
    public boolean checkStatus() {
        return this.status.equals(UserStatus.DISABLED) == false &&
                this.expiredTime.isAfter(LocalDateTime.now());
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
