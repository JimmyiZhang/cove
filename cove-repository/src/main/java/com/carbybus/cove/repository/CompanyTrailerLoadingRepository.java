package com.carbybus.cove.repository;

import com.carbybus.cove.domain.entity.company.CompanyTrailerLoading;
import com.carbybus.infrastructure.component.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 公司板车设置仓储
 *
 * @author jimmy.zhang
 * @date 2019-03-28
 */
@Repository
public interface CompanyTrailerLoadingRepository extends BaseRepository<CompanyTrailerLoading> {

    /**
     * 获取公司的板车设置
     *
     * @param companyId 公司编码
     * @return 公司板车设置集合
     * @author jimmy.zhang
     * @date 2019/43/19
     */
    List<CompanyTrailerLoading> selectByCompany(long companyId);
}
