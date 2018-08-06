package com.readwrite.conf.redis;


import com.readwrite.lock.DistributedLock;
import com.readwrite.lock.RedisDistributedLock;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;


@Configuration
@AutoConfigureAfter(RedisCacheConfig.class)
public class DistributedLockAutoConfiguration {


    /**
     * ConditionalOnBean spring容器中存在对应的实例 会自动去寻找
     * @param redisTemplate
     * @return
     */
    @Bean
    public DistributedLock redisDistributedLock(RedisTemplate<Object, Object> redisTemplate){
        return new RedisDistributedLock(redisTemplate);
    }
}
