package plus.cove.jazzy.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import plus.cove.infrastructure.security.UniteSecurityConfig;

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
    private UniteSecurityConfig securityConfig;
    @Autowired
    private WebSecurityFilter jwtFilter;

    /**
     * 设置web安全细节
     * 定义那些需要被保护，那些不需要保护
     * 不需要验证
     * .antMatchers("").permitAll()
     * 需要验证
     * .anyRequest().authenticated()
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
            // 不需要验证
            .antMatchers(securityConfig.getPermitUrls()).permitAll()
            .antMatchers(securityConfig.getActuatorUrl()).hasRole(securityConfig.getActuatorRole())
            // 其他验证
            .anyRequest().authenticated()
            .and()
            // 关闭X-Frame-Options
            .headers().frameOptions().disable()
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
        web.ignoring().antMatchers(HttpMethod.HEAD, "/**");
    }
}
