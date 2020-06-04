# Cove = Connected Vendor
项目模板

--- 
# 主要思想
- 默认最佳实践配置，支持自定义
- 兼容原来使用方式，增加实用功能，减少学习的成本

---

# 使用技术
- Spring Boot 2.2.x (Hikari)
- lombok 1.18.x
- ORO: orika 1.5.x
- ORM: mybatis & mybatisPlus 3.1.x
- jwt: java-jwt 3.4.x

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

# 基础设施层
- caching 缓存相关
CachingUtils,提供基本的获取，更新，删除操作，提供与Cacheable一样的功能。 cache可以根据配置，启用SIMPLE或REDIS, 其中redis缓存可以单独设置过期实践，具体参见BaseRedisCacheManager
- component 组件相关
实体基类，BaseEntity及实现DefaultEntity，包括Long类型主键和生成主键的valueOf方法
仓储基类，BaseRepository,包括增删改查基本操作
枚举基类，BaseEnum，所有涉及固定单值的建议用枚举，此基类主要实现前端序列化生成int类型而名称，具体见BaseEnumDeserialzer和BaseEnumSerializer
返回基类，ActionResult, 所有api返回基类，统一返回结果code=0则返回成功，data中包括可能的返回结果，其余为返回失败，message中有具体的返回信息，可以在application中的非获取方法中作为返回值
返回页码，PageModel页码输入，包括当前页和页大小，PageResult，页码返回，包括总记录数，总页数
- converter 转换相关
DefaultConverter，通用转换，支持同名，同类型的转换
- exception 异常相关
BusinessException业务基类，非受检基类
- generator 生成器
KeyGenerator,主键生成，提供Snowflake算法的实现，同时实现奇偶随机，保证取模概率一样
- interceptor 拦截器
ApiIdempotentInterceptor 幂等拦截器，保证接口只调用一次
LogTracingInterceptor 日志跟踪拦截器
- jwt jwt相关
JwtUtils jwt生成，提供生成jwt创建（采用HMAC512加密方式），解密，校验，刷新操作
- mybatis
SqlStatement+SqlLike, 提供like过滤特殊符号及自动添加%（可配置）
- system 系统配置
提供各种环境，可以生成各种默认密码，默认随机码
- utils 实用工具类
- validator javax-validation实现类
包括中文，手机号等


# 配置管理
所有的配置管理，UniteXXXConfig,可以对各种功能进行配置，根据最佳实践提供默认值
- UniteCacheConfig 缓存配置类，提供缓存的基本配置，包括默认过期时间（对redis有作用，建议所有缓存均设置过期实践），可以通过配置进行调整
- UniteJsonConfig json配置类，提供json序列化配置，包括设置时间格式，设置实体Long类型和json字符串类型的转换
- UniteHttpConfig http配置类，提供跨域设置，跨域预检有效期，跨域允许的源地址等，可以通过properties对不同环境进进行设置
- UniteJwtConfig jwt配置类，包括token过期时间，密钥，主题，发行人等设置


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

