package com.juandavyc.gatewayserver.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class GatewayRoutesConfig {


    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {

        return routeLocatorBuilder.routes()
                .route("participants-route", p -> p
                        .path("/api/participants/**")
                        .filters(f -> f
                                .rewritePath("/api/participants(?<segment>/?.*)", "/participants${segment}")
                        )
                        .uri("lb://participants"))

                .route("evaluations-route", p -> p
                        .path("/api/evaluations/**")
                        .filters(f -> f
                                .rewritePath("/api/evaluations(?<segment>/?.*)", "/evaluations${segment}")
                        )
                        .uri("lb://evaluations"))

                .route("judges-route", p -> p
                        .path("/api/judges/**")
                        .filters(f -> f
                                .rewritePath("/api/judges(?<segment>/?.*)", "/judges${segment}")
                        )
                        .uri("lb://judges"))

                .route("rankings-route", p -> p
                        .path("/api/rankings/**")
                        .filters(f -> f
                                .rewritePath("/api/rankings(?<segment>/?.*)", "/rankings${segment}")
                        )
                        .uri("lb://ranking"))
                .build();
    }
}
