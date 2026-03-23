package com.example.springjpa.configuration;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.SocketOptions;
import io.lettuce.core.SslOptions;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

@Configuration
@EnableRedisRepositories
public class RedisConfig {
 @Value("${spring.data.redis.host}")
private String redisHost;

@Value("${spring.data.redis.port}")
private int redisPort;

@Value("${spring.data.redis.username:}")
private String redisUsername;

@Value("${spring.data.redis.password}")
private String redisPassword;

@Value("${spring.redis.ssl:false}")

private boolean sslEnabled;


    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(redisHost);
        config.setPort(redisPort);
        
        if (redisUsername != null && !redisUsername.isEmpty()) {
            config.setUsername(redisUsername);
        }
        config.setPassword(redisPassword);

        var clientOptionsBuilder = ClientOptions.builder()
                .socketOptions(SocketOptions.builder()
                        .connectTimeout(Duration.ofSeconds(10))
                        .build());

        if (sslEnabled) {
            // Cấu hình SSL với InsecureTrustManagerFactory để trust all certificates (cho Redis Labs)
            SslOptions sslOptions = SslOptions.builder()
                    .sslContext(sslContextBuilder -> 
                        sslContextBuilder.trustManager(InsecureTrustManagerFactory.INSTANCE)
                    )
                    .build();
            
            clientOptionsBuilder.sslOptions(sslOptions);
        }

        var clientConfigBuilder = LettuceClientConfiguration.builder()
                .clientOptions(clientOptionsBuilder.build());

        if (sslEnabled) {
            clientConfigBuilder.useSsl();
        }

        LettuceClientConfiguration clientConfig = clientConfigBuilder.build();

        return new LettuceConnectionFactory(config, clientConfig);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }
}
