package org.example.fichanewteam.plantilla.cache

import com.github.benmanes.caffeine.cache.Caffeine
import com.github.benmanes.caffeine.cache.Cache
import org.example.fichanewteam.config.Config
import org.example.models.Personal
import java.util.concurrent.TimeUnit


fun providePersonalCache(capacity : Long, duration : Int): Cache<Long, Personal>{
    return Caffeine.newBuilder()
        .maximumSize(capacity)
        .expireAfterWrite(10, TimeUnit.MINUTES)
        .build<Long, Personal>()
}
