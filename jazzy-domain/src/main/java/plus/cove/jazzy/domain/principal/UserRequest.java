package plus.cove.jazzy.domain.principal;

import lombok.Data;

/**
 * 用户凭证
 * 获取当前用户使用
 * 通过缓存保存，以userId作为key值
 *
 * @author Jimmy.Zhang
 */
@Data
public class UserRequest {
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 请求ip
     */
    private String source;

    /**
     * 请求版本
     */
    private String version;

    /**
     * 设备
     */
    private String device;

    /**
     * 请求路由
     */
    protected String router;

    public void withPrincipal(UserPrincipal user) {
        if (user == null) {
            return;
        }

        this.userId = user.getUserId();
        this.userName = user.getUserName();
    }
}