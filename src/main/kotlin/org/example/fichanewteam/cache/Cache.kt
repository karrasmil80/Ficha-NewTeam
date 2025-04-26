package org.example.fichanewteam.cache

import com.github.benmanes.caffeine.cache.Caffeine
import org.example.models.Personal
import java.util.concurrent.TimeUnit

@Singleton
class Cache {
    fun providePersonalCache(capacity: Long, duration: Int): Cache<Int, Personal>{
        return Caffeine.newBuilder()
            .maximumSize(capacity)
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .build<Int, Personal>()
    }
}