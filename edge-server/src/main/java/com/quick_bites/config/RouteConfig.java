package com.quick_bites.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RouteConfig {

    @Bean
    public RouteLocator quickBitesRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                // Restaurant routes
                .route("restaurant-route", r -> r.path("/restaurant/**")
                        .filters(f -> f.rewritePath("/(?<remaining>.*)", "/${remaining}"))
                        .uri("lb://RESTAURANT-MS"))

                // User routes
                .route("user-route", r -> r.path("/user/**")
                        .filters(f -> f.rewritePath("/(?<remaining>.*)", "/${remaining}"))
                        .uri("lb://USER-MS"))

                // Rider routes
                .route("rider-route", r -> r.path("/rider/**")
                        .filters(f -> f.rewritePath("/rider/(?<remaining>.*)", "/${remaining}"))
                        .uri("lb://RIDER-MS"))
                .build();
    }


}
