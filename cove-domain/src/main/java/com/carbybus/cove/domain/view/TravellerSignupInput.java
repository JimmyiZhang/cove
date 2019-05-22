package com.carbybus.cove.domain.view;

import com.carbybus.cove.domain.entity.account.Account;
import com.carbybus.cove.domain.entity.account.Traveller;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


/**
 * 用户注册输入
 *
 * @author jimmy.zhang
 * @date 2019-05-20
 */
@Data
@Accessors(chain = true)
public class TravellerSignupInput {
    @NotEmpty(message = "账号不能为空")
    @Size(min = 4, max = 32, message = "账号5-32个字符")
    private String email;

    @NotEmpty(message = "名称不能为空")
    @Size(min = 4, max = 32, message = "名称5-32个字符")
    private String name;

    @NotEmpty(message = "密码不能为空")
    @Size(min = 4, max = 32, message = "密码5-32个字符")
    private String password;

    /**
     * 转化为账号
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-05-20
     */
    public Account convertToAccount() {
        return Account.create(this.email, this.password);
    }

    /**
     * 转换为旅行者
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-05-20
     */
    public Traveller convertToTraveller() {
        return Traveller.create(this.name);
    }
}