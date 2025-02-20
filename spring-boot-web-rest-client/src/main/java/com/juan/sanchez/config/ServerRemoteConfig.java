package com.juan.sanchez.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ServerRemoteConfig {

    @Bean
    String serverRemoteUri(ServerRemoteProperties serverRemoteProperties) {
        return serverRemoteUriBuilder(serverRemoteProperties.getHost(), serverRemoteProperties.getPort());
    }

    private String serverRemoteUriBuilder(String host, Integer port) {
        return host + ":" + port;
    }

}
