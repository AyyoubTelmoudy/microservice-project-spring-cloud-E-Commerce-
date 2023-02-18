package com.example.billignservice.securityconfig;

import com.example.billignservice.entities.Bill;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.client.KeycloakClientRequestFactory;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

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



}