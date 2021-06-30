# Cove = Connected Vendor
项目模板

--- 
# 主要思想
- 默认最佳实践配置，支持自定义
- 兼容原来使用方式，增加实用功能，减少学习的成本

---

# 技术选型
- Spring Boot 2.3.x (HikariCP)
- lombok 1.18.x
- ORO: orika 1.5.x
- ORM: mybatis & tk.mybatis & pagehelper
- jwt: java-jwt 3.4.x

---

# 框架介绍
- 实体类：BaseEntity: DefaultEntity, DeDefaultTimeEntity 简化和优化实体类的构建，使用静态方法创建对象
- 转换类：BaseConverter: DefaultConverter 简化对象转化，提供对象转化基类
- 数据层：BaseRepository 提供基本CRUD操作，无需xml文件和SQL语句，兼容原myBatis使用方式
- 表现层：提供常用基类支持和统一异常拦截，以及swagger配置，log4j配置
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
- infrastructure-mybatis 对mybatis的封装，支持常见CURD操作，支持like过滤
- infrastructure-excel 对easyExcel的封装，支持导入到对象，对象导出excel(注解方式)
- service-document 对文档操作的汇总，包括excel操作等

---

# 基础设施层 infrastructure
- caching 缓存相关
CachingUtils,提供基本的获取，更新，删除操作，提供与Cacheable一样的功能。 cache可以根据配置，启用SIMPLE或REDIS, 其中redis缓存可以单独设置过期实践，具体参见BaseRedisCacheManager
支持Caffeine，可以设置过期时间
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
DailyNumberGenerator，提供一天内不重复的Integer数值
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


# 配置管理 infrastructure 
所有的配置管理，UniteXXXConfig,可以对各种功能进行配置，根据最佳实践提供默认值
- UniteCacheConfig 缓存配置类，提供缓存的基本配置，包括默认过期时间（对redis有作用，建议所有缓存均设置过期实践），可以通过配置进行调整
- UniteJsonConfig json配置类，提供json序列化配置，包括设置时间格式，设置实体Long类型和json字符串类型的转换
- UniteHttpConfig http配置类，提供跨域设置，跨域预检有效期，跨域允许的源地址等，可以通过properties对不同环境进进行设置
- UniteJwtConfig jwt配置类，包括token过期时间，密钥，主题，发行人等设置


---

# 特殊功能
可以进一步改造为注解模式
## 限流控制-针对登陆错误次数限制，短信发送次数限制
```dtd
    // 创建限流条件，包括限流目标，限流类别，限流次数，支持按天，按小时，按分钟控制
    LimitingCondition limCondition = LimitingCondition.createWithDay(input.getUserName(), "login-failure", 3);
    LimitingTarget limiting = facilityApp.loadLimitingTarget(limCondition);
    boolean isLimit = LimitingTarget.exceedLimit(limiting);
```

## 版本控制-用于一次性使用，比如充值等，也可用于重复提交
```dtd
    // step1
    // 获取对象随机数，输出code和random
    VersioningTarget target = VersioningTarget.of();
    facilityApp.saveVersioningTarget(target);

    // step2
    // 创建版本条件, 使用上一步的code并验证random
    VersioningCondition verCondition = VersioningCondition.from(input.getMessageCode());
    VersioningTarget versioning = facilityApp.loadVersioningTarget(verCondition);
    boolean isVersion = VersioningTarget.invalidRandom(versioning, input.getMessageRandom());
```

---


# 基本约定
- 应用层Application:
- findOneByXXX从缓存中获取单个实体
- findManyByXXX从缓存中获取列表
- loadOneByXXX从存储中获取单个实体
- loadManyByXXX从存储中获取返回列表

- 存储层Repository
- selectOne[基类自带]查询实体对应的所有属性，一个结果
- selectMany[基类自带]查询实体对应的所有属性，多个结果
- selectPart[需要实现]查询部分，列出某些常用属性，一般为了性能（排除非必须大字段或较多字段）
- selectFull[需要实现]查询全部，根据输出查询结果，一般需要联合查询，包括非本实体的字段

---

# 编译方式(针对java15)
- 通过Maven Tool生成war文件
  在project setting中新建maven，command line中指定JAVA_HOME_15的参数，例如： install -D "JAVA_HOME_15=C:\Program Files\Java\jdk-15.0.2" -f pom.xml
  在maven tools运行即可
- 通过Build and run直接运行
  在application中新建application，Environment variables中指定JAVA_HOME_15的参数，例如JAVA_HOME_15=C:\Program Files\Java\jdk-15.0.2
  直接运行即可
- 通过脚本develop.sh生成war文件
  直接Java参数JAVA_HOME_15即可
  
# 观察者模式-基于Spring的事件机制
定义事件
```
  @Component("loggingEventListener")
  public class LoggingEventListener {
    // 注册UserRequest事件
    @Async
    @EventListener
    public void saveLog(UserRequest event) {
      System.out.println("日志");
    }
  }
```

使用事件

```
    @Autowired
    ApplicationContext appContext;
    public ActionResult login(UserLoginInput input) {
      ...
      UserRequest userRequest = new UserRequest();
      // 触发事件
      appContext.publishEvent(userRequest);
    }
```


# 参考资料
[effective java](https://jiapengcai.gitbooks.io/effective-java/content/)
[HikariCP](http://brettwooldridge.github.io/HikariCP/)
[apache commons](http://commons.apache.org/)
[spring boot](https://docs.spring.io/spring-boot/docs/2.1.3.RELEASE/)
[MyBatis](http://www.mybatis.org/mybatis-3/zh/index.html)
[MyBatisPlus](https://mp.baomidou.com/)
[crown](https://caratacus.github.io/)
[ShardingSphere](https://shardingsphere.apache.org/document/current/cn/overview/)
