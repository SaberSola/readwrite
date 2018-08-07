package com.readwrite.conf.ratelimt;


import com.readwrite.conf.RedisLimitBootStrap;
import com.readwrite.conf.redis.RedisCacheConfig;
import com.readwrite.ratelimtit.RateLimiterClient;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

@Configuration
@AutoConfigureAfter(RedisCacheConfig.class)
public class RateLimiterAutoConfiguration {

    private StringRedisTemplate stringRedisTemplate;

    public RateLimiterAutoConfiguration(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    private DefaultRedisScript<Long> rateLimiterLua() {
        DefaultRedisScript<Long> defaultRedisScript = new DefaultRedisScript<Long>();
        defaultRedisScript.setLocation(new ClassPathResource("rate_limiter.lua"));
        defaultRedisScript.setResultType(Long.class);
        return defaultRedisScript;
    }


    @Bean
    @ConditionalOnMissingBean(name = "rateLimiterClient")
    public RateLimiterClient rateLimiterClient() {
        return new RateLimiterClient(stringRedisTemplate, rateLimiterLua());
    }

    @Bean
    public RedisLimitBootStrap redisLimitBootstrap(RateLimiterClient rateLimiterClient) {
        final RedisLimitBootStrap redisLimitBootStrap = new RedisLimitBootStrap(rateLimiterClient);
        return redisLimitBootStrap;
    }
}
