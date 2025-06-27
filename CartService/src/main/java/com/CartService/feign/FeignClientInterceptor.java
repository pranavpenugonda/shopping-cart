package com.CartService.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Collection;
import java.util.stream.Collectors;

@Configuration
public class FeignClientInterceptor {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            var authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication instanceof UsernamePasswordAuthenticationToken auth) {
                Object credentials = auth.getCredentials();
                if (credentials instanceof String token) {
                    template.header("Authorization", "Bearer " + token);
                }

                String username = auth.getName();
                Collection<?> authorities = auth.getAuthorities();

                String roles = authorities.stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(","));

                template.header("X-User-Name", username);
                template.header("X-User-Roles", "[" + roles + "]");
            }
        };
    }
}
