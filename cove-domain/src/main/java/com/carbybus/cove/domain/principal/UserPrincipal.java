package com.carbybus.cove.domain.principal;

import com.carbybus.cove.domain.entity.company.EmployeeCategory;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 用户凭证
 * 获取当前用户使用
 * 通过缓存保存，以userId作为key值
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "init")
public class UserPrincipal {
    /**
     * 用户编号
     */
    private long userId;

    /**
     * 用户类型
     */
    private EmployeeCategory userCategory;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 所属公司
     */
    private long companyId;
}