package plus.cove.jazzy.api.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.*;
import plus.cove.infrastructure.component.PageHelper;
import plus.cove.infrastructure.mybatis.helper.MybatisPageHelper;
import plus.cove.infrastructure.mybatis.interceptor.SqlStatementInterceptor;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.Collections;

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
    @Autowired
    private TransactionManager transactionManager;

    @Bean
    public SqlStatementInterceptor likeInterceptor() {
        SqlStatementInterceptor statement = new SqlStatementInterceptor();
        return statement;
    }

    @Bean
    public PageHelper pageHandler() {
        PageHelper helper = new MybatisPageHelper();
        return helper;
    }

    public TransactionInterceptor transactionInterceptor() {
        // 事务管理规则，设置的事务管理属性
        TransactionAttributeSource source = new NameMatchTransactionAttributeSource();

        // 设置“增删改”的事务管理
        // 设置隔离级别-存在事务则加入其中，不存在则新建事务
        RuleBasedTransactionAttribute writeTrans = new RuleBasedTransactionAttribute();
        writeTrans.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        writeTrans.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        writeTrans.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);

        // 设置“查”的事务管理
        // 设置隔离级别-存在事务则挂起该事务，执行当前逻辑，结束后再恢复上下文事务
        RuleBasedTransactionAttribute readTrans = new RuleBasedTransactionAttribute();
        readTrans.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        readTrans.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);
        readTrans.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        // 设置事务是否“只读”（非必需，只是声明该事务中不会进行修改数据库的操作，可减轻由事务造成的数据库压力，属于性能优化的推荐配置）
        readTrans.setReadOnly(true);

        // 实例化事务拦截器
        TransactionInterceptor txAdvice = new TransactionInterceptor(transactionManager, source);
        return txAdvice;
    }
}


