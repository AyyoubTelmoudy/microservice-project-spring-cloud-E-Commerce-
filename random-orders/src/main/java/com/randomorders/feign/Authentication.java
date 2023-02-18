package com.randomorders.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class Authentication {
    @Value("${keycloak.auth-server-url}")
    private String keycloakAddress;
     @Autowired
    RestTemplate restTemplate;
    public String getAccessToken()
    {
        MultiValueMap<String,String> credentials=new LinkedMultiValueMap<>();
        credentials.add("client_id","products-app");
        credentials.add("username","user1");
        credentials.add("password","1234");
        credentials.add("grant_type","password");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(credentials, headers);
        KeycloakResponse response =
                restTemplate.exchange("http://host.docker.internal:8080/realms/products-app/protocol/openid-connect/token",
                        HttpMethod.POST,
                        entity,
                        KeycloakResponse.class).getBody();
        return response.getAccess_token();
    }
}
