package org.example.fichanewteam.di

import com.github.michaelbull.result.binding
import org.example.fichanewteam.plantilla.cache.*
import org.koin.dsl.module
import org.koin.core.module.dsl.singleOf
import org.example.fichanewteam.plantilla.service.PlantillaServiceImpl
import org.example.fichanewteam.plantilla.repositories.PlantillaRepositoryImpl
import org.example.fichanewteam.plantilla.service.PlantillaService
import org.example.fichanewteam.plantilla.storage.PlantillaStorage
import org.example.fichanewteam.plantilla.storage.PlantillaStorageCsv
import org.example.fichanewteam.plantilla.storage.PlantillaStorageJson
import org.example.fichanewteam.config.Config
import org.jdbi.v3.core.Jdbi
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf


val appModule = module {

    // Repository
    singleOf(::PlantillaRepositoryImpl)

    // Service
    singleOf(::PlantillaServiceImpl) {
        bind<PlantillaService>()
    }

    //Storage
    singleOf(::PlantillaStorageCsv)
    singleOf(::PlantillaStorageJson)






}
