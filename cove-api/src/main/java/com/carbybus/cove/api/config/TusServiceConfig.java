package com.carbybus.cove.api.config;

import me.desair.tus.server.TusFileUploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TusServiceConfig {
    @Value("#{servletContext.contextPath}")
    private String servletContextPath;

    @Bean
    public TusFileUploadService tusFileUploadService() {
        String uploadPath = "D:\\Projects\\Cove\\cove-api\\uploads";

        return new TusFileUploadService()
                .withStoragePath(uploadPath)
                .withDownloadFeature()
                .withUploadURI(servletContextPath + "/files/upload")
                .withThreadLocalCache(true);
    }
}
