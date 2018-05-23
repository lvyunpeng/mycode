package com.sandbox.user;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class CacheRedisConfig {
    @Value("${bmg.redis.cache.host}")
    private String host;

    @Value("${bmg.redis.cache.port}")
    private int port;

    @Value("${bmg.redis.pool.max-active}")
    private int maxActive;

    @Value("${bmg.redis.pool.max-idle}")
    private int maxIdle;

    @Value("${bmg.redis.pool.min-idle}")
    private int minIdle;

    @Value("${bmg.redis.pool.max-wait}")
    private int maxWait;


    @Bean(name = "cacheRedisConnectionFactory")
    public RedisConnectionFactory secondaryRedisConnectionFactory() {

        JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
        redisConnectionFactory.setHostName(host);
        redisConnectionFactory.setPort(port);
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMinIdle(minIdle);
        poolConfig.setMaxWaitMillis(maxWait);
        redisConnectionFactory.setPoolConfig(poolConfig);
        redisConnectionFactory.setUsePool(true);
        return redisConnectionFactory;
    }

    @Bean(name = "cacheStringRedisTemplate")
    public StringRedisTemplate secondaryStringRedisTemplate(@Qualifier("cacheRedisConnectionFactory") RedisConnectionFactory cf) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(cf);
        return stringRedisTemplate;
    }

}
