package org.example.fichanewteam.di

import com.github.benmanes.caffeine.cache.Cache
import org.example.fichanewteam.plantilla.cache.providePersonalCache
import org.example.fichanewteam.config.Config
import org.example.fichanewteam.plantilla.cache.*
import org.jdbi.v3.core.Jdbi
import org.koin.core.module.dsl.bind
import org.koin.dsl.module
import org.koin.core.module.dsl.singleOf


val appModule = module {
    singleOf(::Config)

    singleOf(::provideDatabaseManager) {
        bind<Jdbi>()
    }

    singleOf(::providePersonalCache){
        bind(Cache<Int>)
    }
}