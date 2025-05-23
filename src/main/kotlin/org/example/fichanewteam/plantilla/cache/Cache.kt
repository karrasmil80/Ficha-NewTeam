package org.example.fichanewteam.plantilla.cache

import com.github.benmanes.caffeine.cache.Caffeine
import com.github.benmanes.caffeine.cache.Cache
import org.example.fichanewteam.plantilla.models.Plantilla
import java.util.concurrent.TimeUnit
//PARTE BUENA
fun providePersonalCache(): Cache<Int, Plantilla>{
    return Caffeine.newBuilder()
        .maximumSize(5)
        .expireAfterWrite(1, TimeUnit.MINUTES)
        .build<Int, Plantilla>()
}
//PARTE BUENA
