package com.carbybus.cove.domain.principal;

import lombok.Data;
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
public class UserPrincipal {
    /**
     * 用户编号
     */
    private long userId;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 初始化用户
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-05-27
     */
    public static UserPrincipal init() {
        UserPrincipal principal = new UserPrincipal();
        principal.setUserId(1L);

        return principal;
    }
}