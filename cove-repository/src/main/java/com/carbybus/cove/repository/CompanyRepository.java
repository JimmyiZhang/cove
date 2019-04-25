package com.carbybus.cove.repository;

import com.carbybus.cove.domain.entity.company.Company;
import com.carbybus.infrastructure.component.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * 公司仓储
 *
 * @author jimmy.zhang
 * @date 2019-03-28
 */
@Repository
public interface CompanyRepository extends BaseRepository<Company> {

    /**
     * 根据公司名获取公司信息
     *
     * @param name 公司名
     * @return 公司名对应的公司，没有记录返回null
     * @author jimmy.zhang
     * @date 2019/43/19
     */
    Company selectByName(String name);
}
