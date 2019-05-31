package com.carbybus.cove.api.config;

import com.carbybus.infrastructure.jwt.UniteJwtConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * web安全配置
 *
 * @author jimmy.zhang
 * @date 2019-05-14
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UniteJwtConfig jwtConfig;

    @Autowired
    private JwtAuthenticationFilter jwtFilter;

    /**
     * 设置web安全细节
     * 定义那些需要被保护，那些不需要保护
     *
     * @param
     * @return
     * @author jimmy.zhang
     * @date 2019-05-14
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 认证配置
                .authorizeRequests()
                .antMatchers("/admin/**").authenticated()
                .anyRequest().permitAll()
                .and()

                // 不需要csrf(cross-site request forgery)
                .csrf().disable()
                // 不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilter, FilterSecurityInterceptor.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }
}
