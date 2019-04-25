package com.carbybus.cove.application;

import com.carbybus.cove.domain.entity.company.Company;
import com.carbybus.infrastructure.component.ActionResult;
import com.carbybus.infrastructure.component.BaseApplication;

/**
 * 公司应用
 *
 * @author jimmy.zhang
 * @date 2019-03-28
 */
public interface CompanyApplication extends BaseApplication<Company> {
    /**
     * 创建
     *
     * @param company 实体
     * @return 创建结果
     * @author jimmy.zhang
     * @date 2019-04-19
     */
    ActionResult create(Company company);

    /**
     * 根据id获取
     *
     * @param id 编号
     * @return 获取结果
     * @author jimmy.zhang
     * @date 2019-04-19
     */
    Company getById(long id);
}
