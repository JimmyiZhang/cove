package com.carbybus.cove.application.impl;

import com.carbybus.cove.application.CompanyApplication;
import com.carbybus.cove.domain.entity.company.Company;
import com.carbybus.cove.domain.entity.company.CompanyTrailerLoading;
import com.carbybus.cove.domain.exception.CompanyError;
import com.carbybus.cove.repository.CompanyRepository;
import com.carbybus.cove.repository.CompanyTrailerLoadingRepository;
import com.carbybus.infrastructure.component.ActionResult;
import com.carbybus.infrastructure.component.impl.DefaultApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 公司应用
 *
 * @author jimmy.zhang
 * @date 2019-04-19
 */
@Service
public class CompanyApplicationImpl extends DefaultApplication<CompanyRepository, Company> implements CompanyApplication {
    @Autowired
    private CompanyTrailerLoadingRepository loadingRep;

    @Override
    public ActionResult create(Company company) {
        ActionResult result = new ActionResult();

        // 检查是否存在
        Company dbCompany = repository.selectByName(company.getName());
        if (dbCompany != null) {
            result.fail(CompanyError.EXISTED_ACCOUNT);
            return result;
        }

        // 保存数据
        this.save(company);

        List<CompanyTrailerLoading> loadings = company.getLoadings();
        for (CompanyTrailerLoading loading : loadings) {
            loadingRep.insert(loading);
        }
        result.succeed();
        return result;
    }

    @Override
    public Company getById(long id) {
        Company company = repository.selectById(id);
        List<CompanyTrailerLoading> loadings = loadingRep.selectByCompany(id);

        company.setLoadings(loadings);
        return company;
    }
}
