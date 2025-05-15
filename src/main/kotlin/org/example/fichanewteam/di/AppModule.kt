package org.example.fichanewteam.di


import com.github.benmanes.caffeine.cache.Cache
import org.koin.dsl.module
import org.koin.core.module.dsl.singleOf
import org.example.fichanewteam.config.Config
import org.example.fichanewteam.plantilla.cache.providePersonalCache
import org.example.fichanewteam.plantilla.models.Plantilla
import org.example.fichanewteam.plantilla.viewmodel.PlantillaViewModel
import org.jdbi.v3.core.Jdbi
import org.koin.core.module.dsl.bind

val appModule = module {

    //SINGLETON DE CONFIG
    singleOf(::Config)

    //SINGLETON DEL VIEW MODEL
    singleOf(::PlantillaViewModel)

    //SINGLETON DE JDBI MANAGER
    singleOf(::providePersonalCache) {
        bind<Cache<Int, Plantilla>>()
    }




}
