package org.example.fichanewteam.di

import com.github.benmanes.caffeine.cache.Cache
import com.github.michaelbull.result.Result
import org.koin.dsl.module
import org.koin.core.module.dsl.singleOf
import org.example.fichanewteam.config.Config
import org.example.fichanewteam.plantilla.cache.providePersonalCache
import org.example.fichanewteam.plantilla.models.Plantilla
import org.example.fichanewteam.plantilla.service.PlantillaService
import org.example.fichanewteam.plantilla.viewmodel.PlantillaViewModel
import org.example.fichanewteam.plantilla.service.PlantillaServiceImpl
import org.example.fichanewteam.plantilla.repositories.PlantillaRepositoryImpl
import org.example.fichanewteam.plantilla.repositories.PlantillaRepository
import org.example.fichanewteam.database.provideDatabaseManager
import org.example.fichanewteam.plantilla.dao.PlantillaDao
import org.example.fichanewteam.plantilla.dao.providePlantillaDao
import org.example.fichanewteam.controllers.HelloController
import org.example.fichanewteam.database.JdbiManager
import org.example.fichanewteam.plantilla.models.Jugador
import org.example.fichanewteam.plantilla.models.Entrenador
import org.example.fichanewteam.plantilla.validator.PlantillaValidator
import org.jdbi.v3.core.Jdbi
import org.koin.core.module.dsl.bind
import org.lighthousegames.logging.logging

val appModule = module {

    single<Jdbi> { provideDatabaseManager(
        config = get()
    ) }

    single<PlantillaDao> { providePlantillaDao(
        jdbi = get()
    ) }

    single<Cache<Long, Plantilla>> { providePersonalCache(
        config = get()
    ) }

    single<Config> { Config() }

    single<PlantillaRepository> { PlantillaRepositoryImpl(get()) }

    // Servicio
    single<PlantillaServiceImpl> {
        val repo = get<PlantillaRepository>()
        val config = get<Config>()
        val cache = get<Cache<Long, Plantilla>>()
        println("Creando PlantillaServiceImpl con repo=$repo, config=$config, cache=$cache")
        PlantillaServiceImpl(repo as PlantillaRepositoryImpl, config, cache)
    }

    // ViewModel
    single { PlantillaViewModel(servicio = get() as PlantillaServiceImpl) }

}
