package com.juan.sanchez.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties(prefix = "server.remote")
class ServerRemoteProperties {

    private final String host;

    private final Integer port;

    @ConstructorBinding
    ServerRemoteProperties(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }

}
