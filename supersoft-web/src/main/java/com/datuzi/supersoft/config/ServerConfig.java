package com.datuzi.supersoft.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("supersoft")
@Component
public class ServerConfig {
    private String staticPath;
    private String ftpFileBase;

    public String getStaticPath() {
        return staticPath;
    }

    public void setStaticPath(String staticPath) {
        this.staticPath = staticPath;
    }

    public String getFtpFileBase() {
        return ftpFileBase;
    }

    public void setFtpFileBase(String ftpFileBase) {
        this.ftpFileBase = ftpFileBase;
    }
}
