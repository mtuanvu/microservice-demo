package com.fai.semfour.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", r -> r.path("/users/**", "/permissions/**", "/friends-user/**")
                        .uri("lb://user-service"))
                .route("friend-service", r -> r.path("/friends/**")
                        .uri("lb://friend-service"))
                .build();
    }
}
