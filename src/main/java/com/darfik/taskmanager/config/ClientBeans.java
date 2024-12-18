package com.darfik.taskmanager.config;

import com.darfik.taskmanager.client.ProductsRestClientImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientBeans {

    @Bean
    ProductsRestClientImpl productsRestClient(@Value("${task-manager.services.manager.uri:http://localhost:8080}") String managerBaseUri) {
        return new ProductsRestClientImpl(RestClient.builder()
                .baseUrl(managerBaseUri)
                .build());
    }

}
