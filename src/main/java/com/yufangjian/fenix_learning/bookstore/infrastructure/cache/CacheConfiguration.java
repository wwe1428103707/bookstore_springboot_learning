package com.yufangjian.fenix_learning.bookstore.infrastructure.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfiguration {

    public static final long SYSTEM_DEFAULT_EXPIRES = 4 * 60 *1000;

    @Bean
    public CacheManager configCacheManager(){
        CaffeineCacheManager manager = new CaffeineCacheManager();
        manager.setCaffeine(Caffeine.newBuilder().expireAfterWrite(SYSTEM_DEFAULT_EXPIRES, TimeUnit.MILLISECONDS));

        return manager;
    }

    @Bean(name = "settlement")
    public Cache getSettlementTTLCache(){
        return new CaffeineCache("settlement", Caffeine.newBuilder().expireAfterAccess(SYSTEM_DEFAULT_EXPIRES, TimeUnit.MILLISECONDS).build());
    }

}
