package com.randomorders.securityconfig;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.client.KeycloakClientRequestFactory;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class KeycloakAdapterConfig {

    @Bean
    KeycloakSpringBootConfigResolver springBootConfigResolver(){
        return new KeycloakSpringBootConfigResolver();
    }

    @Bean
    KeycloakRestTemplate keycloakRestTemplate(KeycloakClientRequestFactory clientRequestFactory)
    {
        return  new KeycloakRestTemplate(clientRequestFactory);
    }

    @Bean
    RestTemplate restTemplate()
    {
        return  new RestTemplate();
    }

}