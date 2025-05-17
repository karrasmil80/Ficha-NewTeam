package org.example.fichanewteam.plantilla.cache

import com.github.benmanes.caffeine.cache.Caffeine
import com.github.benmanes.caffeine.cache.Cache
import org.example.fichanewteam.config.Config
import org.example.fichanewteam.plantilla.models.Plantilla
import org.lighthousegames.logging.logging
import java.util.concurrent.TimeUnit

fun providePersonalCache(config : Config): Cache<Long, Plantilla> {
    val logger = logging()
    logger.debug { "Iniciando Cache Caffeine con capacidad ${config.cacheCapacity} y una duracion de ${config.cacheExpiration}" }
    return Caffeine.newBuilder()
        .maximumSize(config.cacheCapacity)
        .expireAfterWrite(config.cacheExpiration, TimeUnit.SECONDS).build()
}
