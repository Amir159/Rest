package com.syncretis.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    final TokenStore tokenStore;
    final JwtAccessTokenConverter accessTokenConverter;
    final DefaultTokenServices tokenServices;

    public ResourceServerConfig(TokenStore tokenStore, JwtAccessTokenConverter accessTokenConverter, DefaultTokenServices tokenServices) {
        this.tokenStore = tokenStore;
        this.accessTokenConverter = accessTokenConverter;
        this.tokenServices = tokenServices;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer config) {
        tokenServices.setSupportRefreshToken(false);
        config.tokenServices(tokenServices);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/**").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN")
        ;
    }
}