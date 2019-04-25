package com.carbybus.cove.domain.entity.company;

import com.baomidou.mybatisplus.annotation.TableName;
import com.carbybus.infrastructure.component.impl.DefaultEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 公司板车设置
 * 板位及对应重驶和空驶设置
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor(staticName = "init")
@EqualsAndHashCode(callSuper = false)
@TableName(value = "company_trailer_loading")
public class CompanyTrailerLoading extends DefaultEntity {
    /**
     * 板车位数
     */
    private int place;

    /**
     * 重载位数
     */
    private int heavy;

    /**
     * 公司编号
     */
    private long companyId;

    /** 
    * 创建 
    * @param  
    * @return  
    * @author jimmy.zhang 
    * @date 2019-04-19 
    */ 
    public static CompanyTrailerLoading create(long companyId){
        CompanyTrailerLoading loading = new CompanyTrailerLoading();
        loading.setCompanyId(companyId);
        return loading;
    }
}