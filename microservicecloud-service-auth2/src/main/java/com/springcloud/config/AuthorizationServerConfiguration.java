package com.springcloud.config;

import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        String finalSecret = "{bcrypt}" + new BCryptPasswordEncoder().encode("123456");

        // 配置两个客户端，一个用于password认证一个用于client认证
        clients.inMemory().withClient("client_1")
                .resourceIds(Utils.RESOURCEIDS.ORDER)
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .scopes("select")
                .authorities("oauth2")
                .secret(finalSecret)
                .and().withClient("client_2")
                .resourceIds(Utils.RESOURCEIDS.ORDER)
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("server")
                .authorities("oauth2")
                .secret(finalSecret);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(new MyRedisTokenStore(redisConnectionFactory))
                .authenticationManager(authenticationManager)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 允许表单认证
        security.allowFormAuthenticationForClients();
    }
}