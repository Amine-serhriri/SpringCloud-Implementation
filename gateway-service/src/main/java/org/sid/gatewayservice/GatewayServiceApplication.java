package org.sid.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

import org.springframework.context.annotation.Bean;

@SpringBootApplication

public class GatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }
    //@Bean
    /* RouteLocator routeLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route(r->r.path("/customers/**").uri("lb://CUSTOMER-SERVICE"))
                .route(r->r.path("/products/**").uri("lb://PRODUCT-SERVICE"))
                .build();
    }*/
    @Bean
    RouteLocator routes(RouteLocatorBuilder builder){
        return builder.routes()
                .route(r->r.path("/restcountries/**")
                        .filters(f->f
                                .addRequestHeader("X-RapidAPI-Key","3250da295dmshe84d0b91714027bp107701jsn04c0327a7915")
                                .addRequestHeader("X-RapidAPI-Host","eu-covid-19-travel.p.rapidapi.com")
                                .rewritePath("/restcountries/(?<segment>.*)","/${segment}")


                        )
                                .uri("https://eu-covid-19-travel.p.rapidapi.com/data")
                        ).build();

    }

    //configuration plus dynamique
    @Bean
    //maintenant le nom du microService vient depuis la requete Url
    DiscoveryClientRouteDefinitionLocator definitionLocator(
            ReactiveDiscoveryClient rdc
            , DiscoveryLocatorProperties properties){
        return new DiscoveryClientRouteDefinitionLocator(rdc,properties);
    }

}
