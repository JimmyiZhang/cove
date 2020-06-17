package plus.cove.jazzy.api.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import plus.cove.infrastructure.mybatis.SqlStatementInterceptor;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * mybatis配置
 *
 * @author jimmy.zhang
 * @date 2019-03-29
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "plus.cove.jazzy.repository")
public class MyBatisConfig {
    @Bean
    public SqlStatementInterceptor likeInterceptor() {
        SqlStatementInterceptor statement = new SqlStatementInterceptor();
        return statement;
    }
}


