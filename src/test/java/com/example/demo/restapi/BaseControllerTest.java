package com.example.demo.restapi;

import org.keycloak.adapters.OidcKeycloakAccount;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;
import java.util.Arrays;
import java.util.HashSet;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BaseControllerTest {
    public void configureSecurityContext(String... roles) {
        final var principal = mock(Principal.class);

        final var account = mock(OidcKeycloakAccount.class);
        when(account.getRoles()).thenReturn(new HashSet<>(Arrays.asList(roles)));
        when(account.getPrincipal()).thenReturn(principal);

        final var authentication = mock(KeycloakAuthenticationToken.class);
        when(authentication.getAccount()).thenReturn(account);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
