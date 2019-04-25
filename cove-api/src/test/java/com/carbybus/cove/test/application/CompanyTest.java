package com.carbybus.cove.test.application;

import com.carbybus.cove.api.ApiApplication;
import com.carbybus.cove.application.CompanyApplication;
import com.carbybus.cove.domain.entity.company.Company;
import com.carbybus.cove.domain.entity.company.CompanyTrailerLoading;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class CompanyTest {
    @Autowired
    private CompanyApplication companyApp;

    private Company company;

    @Before
    public void init() {
        company = Company.create()
                .setDriverFee(10)
                .setFuelFee(20)
                .setIncomeFee(30)
                .setName("车拉车1")
                .setTollFee(50)
                .setSpeedWarning(80);

        List<CompanyTrailerLoading> loadings = new ArrayList<>();
        CompanyTrailerLoading loading1 = CompanyTrailerLoading.create(company.getId());
        loading1.setPlace(6).setHeavy(3);
        loadings.add(loading1);

        company.setLoadings(loadings);
    }

    @Test
    public void create() {
        companyApp.create(this.company);

        Assert.isTrue(true, "创建公司");
    }
}
