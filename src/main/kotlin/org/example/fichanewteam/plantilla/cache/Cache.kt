package org.example.fichanewteam.plantilla.cache

import com.github.benmanes.caffeine.cache.Caffeine
import com.github.benmanes.caffeine.cache.Cache
import org.example.fichanewteam.config.Config
import org.example.models.Personal
import java.util.concurrent.TimeUnit


fun providePersonalCache(config: Config): Cache<Long, Personal>{
    return Caffeine.newBuilder()
        .maximumSize(config.capacity)
        .expireAfterWrite(10, TimeUnit.MINUTES)
        .build()
}
