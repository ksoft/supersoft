package com.datuzi.supersoft.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("supersoft")
@Component
@Data
public class ServerConfig {
    private String contextPath;
    private String ftpFileBase;
}
