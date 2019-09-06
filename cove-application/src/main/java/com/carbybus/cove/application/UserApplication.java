package com.carbybus.cove.application;


import com.carbybus.cove.domain.principal.UserPrincipal;

/**
 * 用户应用
 *
 * @author jimmy.zhang
 * @date 2019-03-28
 */
public interface UserApplication {
    /**
     * 获取用户凭证
     *
     * @param id 用户id
     * @return 用户凭证
     * @author jimmy.zhang
     * @date 2019-03-28
     */
    UserPrincipal findPrincipal(Long id);

    /**
     * 清除凭证
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-07-04
     */
    void clearPrincipal(Long id);
}
