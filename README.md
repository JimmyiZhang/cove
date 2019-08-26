# Cove = Connected Vehicle
项目模板

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
- 实体类：简化和优化实体类的构建，使用静态方法创建对象
- 对象转换：简化对象转化，提供对象转化基类
- 数据层：提供基本CRUD操作，无需xml文件和SQL语句，兼容原myBatis使用方式
- 服务层：提供基本CRUD操作，提供基于Lambda表达式的查询
- 表现层：提供常用基类支持和统一异常拦截，以及swagger配置，异步日志和日志历史删除
- 项目管理：提供基于dependencyManagement的依赖管理和profile的发布方式，灵活方便

---

# 分层介绍
- domain 包括实体类（领域类entity),仓储接口(repository),应用接口(application),显示类(view)
- domain.converter 转换类，领域类与其他类的转化，仅包括特殊转化，同类型和字段名称使用默认转换即可
- domain.exception 异常类，所有的业务异常继承自BusinessError, 所有的异常均有唯一的返回码
- domain.view 显示类，用于接口统一的显示，原则上领域类不对外暴露，保证领域类不影响显示，以及领域类的安全
- domain.repository 仓储接口，继承BaseRepository即可拥有基本实现,同时可以使用mybatis实现负责功能
- domain.application 应用接口，继承BaseApplication即可拥有基本能实现
- repository 仓储实现类，由于使用mybatis,此层仅包括xml文件，可以替换其他数据库实现
- application 应用实现类，按领域进行划分，从仓储获取数据，进行业务处理，然后调用仓储进行保存
- api 应用接口层，用于对外提供接口，包括获取当前用户，请求相关数据，然后调用application处理
- infrastructure 基础设施层，提供无关业务的基础层，原则上可以用于任何项目

---

# 基础设施曾
- caching

# 

---

---

# 基本约定
- 应用层Application:
- findOneByXXX从缓存中获取单个实体
- findManyByXXX从缓存中获取列表
- loadOneByXXX从存储中获取单个实体
- loadManyByXXX从存储中获取返回列表

- 存储层Repository
- selectOne[基类自带]查询实体对应的所有属性，一个结果
- selectList[基类自带]查询实体对应的所有属性，多个结果
- selectPart[需要实现]查询部分，列出某些常用属性，一般为了性能（排除非必须大字段或较多字段）
- selectFull[需要实现]查询全部，根据输出查询结果，一般需要联合查询，包括非本实体的字段

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
