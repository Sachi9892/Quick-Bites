package com.quick_bites.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class RouteConfig {

    @Bean
    public RouteLocator quickBitesRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {

        return routeLocatorBuilder.routes()
                .route(p ->
                        p.path("/quickbites/user/**")
                                .filters(f -> f.rewritePath("/quickbites/user/(?<remaining>.*)", "/${remaining}")
                                        .addResponseHeader("X-Response-Time" , LocalTime.now().toString()))
                                .uri("lb://USER-MS"))
                .route(p ->
                        p.path("/quickbites/restaurant/**")
                                .filters(f -> f.rewritePath("/quickbites/restaurant/(?<remaining>.*)", "/${remaining}"))
                                .uri("lb://RESTAURANT-MS"))
                .build();
    }

}
