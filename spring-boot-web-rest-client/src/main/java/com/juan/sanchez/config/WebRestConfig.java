package com.juan.sanchez.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
class WebRestConfig {

    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder, String serverRemoteUri) {
        return builder.rootUri(serverRemoteUri).build();
    }

    @Bean
    WebClient webClient(WebClient.Builder builder, String serverRemoteUri) {
        return builder.baseUrl(serverRemoteUri).build();
    }

}
