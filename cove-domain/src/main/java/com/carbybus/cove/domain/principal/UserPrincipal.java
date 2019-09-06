package com.carbybus.cove.domain.principal;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 用户凭证
 * 获取当前用户使用
 * 通过缓存保存，以userId作为key值
 *
 * @author Jimmy.Zhang
 */
@Data
@Accessors(chain = true)
@ToString
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

    /**
     * 未知用户
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-09-06
     */
    public static final UserPrincipal UNKNOWN = init(0L, "unknown");

    /**
     * 初始化用户
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-05-27
     */
    public static UserPrincipal init(Long userId, String userName) {
        UserPrincipal principal = new UserPrincipal()
                .setUserId(userId)
                .setUserName(userName);
        return principal;
    }
}