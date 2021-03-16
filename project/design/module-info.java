module cove.infrastructure {
    requires lombok;
    requires java.validation;
    requires java.persistence;
    requires spring.boot;
    requires spring.context;
    requires spring.core;
    requires com.github.benmanes.caffeine;
    requires spring.beans;
    requires spring.context.support;
    requires org.apache.commons.lang3;
    requires spring.webmvc;
    requires org.apache.tomcat.embed.core;
    requires java.jwt;
    requires spring.data.redis;
    requires spring.security.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires spring.boot.autoconfigure;
    requires jakarta.mail;
    requires spring.web;
    requires orika.core;
    requires slf4j.api;
}