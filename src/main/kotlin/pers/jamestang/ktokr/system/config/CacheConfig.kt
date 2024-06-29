package pers.jamestang.ktokr.system.config

import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.cache.caffeine.CaffeineCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.TimeUnit

@Configuration
class CacheConfig {

    @Bean
    fun cacheManager(): CaffeineCacheManager{
        val cacheManager = CaffeineCacheManager()
        cacheManager.setCaffeine(Caffeine.newBuilder()
            .initialCapacity(100)
            .maximumSize(1000)
            .expireAfterWrite(1, TimeUnit.MINUTES))
        return cacheManager
    }
}