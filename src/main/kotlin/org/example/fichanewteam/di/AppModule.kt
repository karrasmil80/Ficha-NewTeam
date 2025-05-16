package org.example.fichanewteam.di


import com.github.benmanes.caffeine.cache.Cache
import org.koin.dsl.module
import org.koin.core.module.dsl.singleOf
import org.example.fichanewteam.config.Config
import org.example.fichanewteam.plantilla.cache.providePersonalCache
import org.example.fichanewteam.plantilla.models.Plantilla
import org.example.fichanewteam.plantilla.service.PlantillaService
import org.example.fichanewteam.plantilla.viewmodel.PlantillaViewModel
import org.example.fichanewteam.plantilla.service.PlantillaServiceImpl
import org.example.fichanewteam.plantilla.storage.PlantillaStorage
import org.example.fichanewteam.plantilla.storage.PlantillaImageStorage
import org.example.fichanewteam.plantilla.storage.PlantillaImgStorageImpl
import org.jdbi.v3.core.Jdbi
import org.koin.core.module.dsl.bind


val appModule = module {

    //SINGLETON DE CONFIG
    singleOf(::Config)

    //SINGLETON DE JDBI MANAGER
    singleOf(::providePersonalCache) {
        bind<Cache<Int, Plantilla>>()
    }

    // SINGLETON VIEW MODEL
    singleOf(::PlantillaViewModel)

    //SINGLETON SERVICE - VIEW MODEL
    singleOf(::PlantillaServiceImpl) {
        bind<PlantillaService>()
    }

    singleOf(::PlantillaImageStorageImpl) {
        bind<PlantillaImageStorage>()
    }







}
