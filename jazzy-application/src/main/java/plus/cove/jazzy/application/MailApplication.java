package plus.cove.jazzy.application;

/**
 * 邮件应用
 * 发送邮件
 *
 * @author jimmy.zhang
 * @date 2019-06-24
 */
public interface MailApplication {
    /**
     * 发送激活邮件
     *
     * @param mail 邮箱地址
     * @param code 激活码
     * @return
     * @author jimmy.zhang
     * @date 2019-06-24
     */
    void sendSignupMail(String mail, String code);
}
