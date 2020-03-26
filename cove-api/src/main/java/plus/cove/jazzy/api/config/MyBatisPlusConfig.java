package plus.cove.jazzy.api.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import plus.cove.infrastructure.mybatis.SqlStatementInterceptor;

/**
 * mybatis-plus配置
 * 可以设置最大行数以及检测慢sql
 *
 * @author jimmy.zhang
 * @date 2019-03-29
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "plus.cove.jazzy.repository")
public class MyBatisPlusConfig {
    /**
     * SQL自动分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paging = new PaginationInterceptor();
        // 设置limit最大为1000
        paging.setLimit(1000);
        return paging;
    }

    @Bean
    public SqlStatementInterceptor likeInterceptor() {
        SqlStatementInterceptor statement = new SqlStatementInterceptor();
        return statement;
    }
}


