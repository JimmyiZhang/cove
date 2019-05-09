# Cove = Connected Vehicle
车联网项目模板
---

# 使用技术
- Spring Boot 2.1.3 (Hikari，logback)
- lombok 1.18.6
- 对象映射: orika 1.5.4
- ORM: mybatisPlus 3.1.0
- jwt: java-jwt 3.4.0
- 分布式数据库中间件: sharding-jdbc 4.0.0-RC1
---

# 框架介绍
- 实体类：简化和优化实体类的构建，使用静态方法常见对象
- 对象转换：简化对象转化，提供对象转化基类
- 数据层：提供基本CRUD操作，无需xml文件和SQL语句，兼容原myBatis使用方式
- 服务层：提供基本CRUD操作，提供基于Lambda表达式的查询
- 表现层：提供常用基类支持和统一异常拦截，以及swagger配置，异步日志和日志历史删除
- 项目管理：提供基于dependencyManagement的依赖管理和profile的发布方式，灵活方便
---
# 参考资料
[effective java](https://jiapengcai.gitbooks.io/effective-java/content/)
[HikariCP](http://brettwooldridge.github.io/HikariCP/)
[apache commons](http://commons.apache.org/)
[spring boot](https://docs.spring.io/spring-boot/docs/2.1.3.RELEASE/)
[MyBatis](http://www.mybatis.org/mybatis-3/zh/index.html)
[MyBatisPlus](https://mp.baomidou.com/)
[crown](https://caratacus.github.io/)
[ShardingSphere](https://shardingsphere.apache.org/document/current/cn/overview/)

# 基本约定
- 应用层Application:
- findByXXX从缓存中获取
- getByXXX从存储中获取

