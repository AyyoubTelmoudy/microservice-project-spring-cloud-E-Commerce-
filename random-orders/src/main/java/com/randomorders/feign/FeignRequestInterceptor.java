package com.randomorders.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class FeignRequestInterceptor implements RequestInterceptor {
    //@Autowired
    //HttpServletRequest request;
    @Autowired
    Authentication authentication;
    @Override
    public void apply(RequestTemplate requestTemplate) {
        //KeycloakAuthenticationToken KeycloakAuthenticationToken= (KeycloakAuthenticationToken) request.getUserPrincipal();
        //KeycloakPrincipal principal=(KeycloakPrincipal)KeycloakAuthenticationToken.getPrincipal();
        //KeycloakSecurityContext session = principal.getKeycloakSecurityContext();
        //String token = session.getTokenString();
        requestTemplate.header("Authorization","Bearer "+authentication.getAccessToken());
    }
}
