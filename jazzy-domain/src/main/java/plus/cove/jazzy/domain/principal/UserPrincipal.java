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
public class UserPrincipal {
    /**
     * 用户编号
     */
    private Long userId;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 头像
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-07-04
     */
    private String userAvatar;

    public String toLog() {
        StringBuilder sb = new StringBuilder(7);
        sb.append("User Principal ==> ")
                .append("user-id: ").append(this.userId)
                .append("user-name: ").append(this.userName)
                .append("user-avatar: ").append(this.userAvatar);
        return sb.toString();
    }

    /**
     * 初始化用户
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-05-27
     */
    public static UserPrincipal init(Long userId, String userName) {
        UserPrincipal principal = new UserPrincipal();
        principal.userId = userId;
        principal.userName = userName;
        return principal;
    }
}