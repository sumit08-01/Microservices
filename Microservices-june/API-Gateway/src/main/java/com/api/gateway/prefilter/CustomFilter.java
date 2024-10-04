package com.api.gateway.prefilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class CustomFilter implements GlobalFilter {

    private static final Logger logger = LoggerFactory.getLogger(GlobalFilter.class);
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        if(request.getURI().toString().contains("/api/student/")){
            // any check here for a specific microservices
        }
        logger.info("Authorization : Before pass to a specific microservice : " + request.getHeaders().getFirst("Authorization"));
//        return chain.filter(exchange); // --> Pre filter
            return chain.filter(exchange).then(Mono.fromRunnable(()-> { // --> After then post Filter work
                ServerHttpResponse response = exchange.getResponse();
                logger.info("Post Filter : --> " + response.getStatusCode());
            }));
    }
}
