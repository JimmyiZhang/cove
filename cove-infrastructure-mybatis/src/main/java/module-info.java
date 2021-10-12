open module cove.infrastructure.mybatis {
    exports plus.cove.infrastructure.mybatis;
    exports plus.cove.infrastructure.mybatis.handler;
    exports plus.cove.infrastructure.mybatis.interceptor;

    requires spring.core;
    requires java.sql;
    requires mybatis.mapper;
    requires mybatis.provider;
    requires org.mybatis;

    requires cove.infrastructure;
}