open module cove.infrastructure.mybatis {
    exports plus.cove.infrastructure.mybatis;
    exports plus.cove.infrastructure.mybatis.handler;
    exports plus.cove.infrastructure.mybatis.interceptor;
    exports plus.cove.infrastructure.mybatis.mapper;

    requires spring.core;
    requires java.sql;
    requires mybatis.mapper;
    requires mybatis.provider;
    requires org.mybatis;
    requires java.persistence;

    requires cove.infrastructure;
}