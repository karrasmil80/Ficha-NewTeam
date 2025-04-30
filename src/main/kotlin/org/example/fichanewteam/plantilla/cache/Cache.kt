package org.example.fichanewteam.plantilla.cache

import com.github.benmanes.caffeine.cache.Caffeine
import com.github.benmanes.caffeine.cache.Cache
import org.example.fichanewteam.config.Config
import org.example.models.Plantilla
import java.util.concurrent.TimeUnit


fun providePersonalCache(config: Config, capacity: Long): Cache<Long, Plantilla>{
    return Caffeine.newBuilder()
        .maximumSize(config.capacity)
        .expireAfterWrite(10, TimeUnit.MINUTES)
        .build<Int, Plantilla>()
}
