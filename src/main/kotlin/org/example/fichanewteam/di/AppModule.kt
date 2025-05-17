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
import org.example.fichanewteam.plantilla.storage.PlantillaStorageImpl
import org.example.fichanewteam.plantilla.repositories.PlantillaRepositoryImpl
import org.example.fichanewteam.plantilla.repositories.PlantillaRepository
import org.example.fichanewteam.database.provideDatabaseManager
import org.example.fichanewteam.plantilla.dao.PlantillaDao
import org.example.fichanewteam.plantilla.dao.providePlantillaDao
import org.example.fichanewteam.plantilla.storage.PlantillaStorageJson
import org.example.fichanewteam.plantilla.storage.PlantillaStorageJsonImpl
import org.example.fichanewteam.plantilla.storage.PlantillaZipStorage
import org.example.fichanewteam.plantilla.storage.PlantillaZipStorageImpl
import org.example.fichanewteam.plantilla.storage.PlantillaImageStorage
import org.example.fichanewteam.plantilla.storage.PlantillaImgStorageImpl
import org.jdbi.v3.core.Jdbi
import org.koin.core.module.dsl.bind


    val appModule = module {

        //SINGLETON DE CONFIG
        singleOf(::Config)

        //SINGLETON JDBI MANAGER
        singleOf(::provideDatabaseManager) {
            bind<Jdbi>()
        }

        // SINGLETON DAO
        singleOf(::providePlantillaDao) {
            bind<PlantillaDao>()
        }

        //SINGLETON DE CACHE
        singleOf(::providePersonalCache) {
            bind<Cache<Long, Plantilla>>()
        }

        // SINGLETON REPOSITORY
        singleOf(::PlantillaRepositoryImpl) {
            bind<PlantillaRepository>()
        }

        //SINGLETON SERVICE - VIEW MODEL
        singleOf(::PlantillaServiceImpl) {
            bind<PlantillaService>()
        }

        //SINGLETON STORAGE - VIEW MODEL
        singleOf(::PlantillaStorageImpl) {
            bind<PlantillaStorage>()
        }

        // SINGLETON REPOSITORY
        singleOf(::PlantillaRepositoryImpl){
            bind<PlantillaRepository>()
        }

        //SINGLETON STORAGE JSON - SERVICE
        singleOf(::PlantillaStorageJsonImpl) {
            bind<PlantillaStorageJson>()
        }

        singleOf(::PlantillaZipStorageImpl) {
            bind<PlantillaZipStorage>()
        }

        singleOf(::PlantillaImgStorageImpl) {
            bind<PlantillaImageStorage>()
        }

        // SINGLETON VIEW MODEL
        singleOf(::PlantillaViewModel)
}
