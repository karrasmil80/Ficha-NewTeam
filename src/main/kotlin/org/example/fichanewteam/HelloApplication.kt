package org.example.fichanewteam

import javafx.application.Application
import javafx.stage.Stage
import org.example.fichanewteam.config.Config
import org.example.fichanewteam.di.appModule
import org.example.fichanewteam.routes.RoutesManager
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.lighthousegames.logging.logging

private val logger = logging()

class HelloApplication : Application(), KoinComponent {

    init {
        startKoin {
            printLogger()
            modules(appModule)
        }
    }

    override fun start(stage: Stage) {
        logger.debug { "Iniciando New-Team APP" }

        // Forzamos la carga de Config aquí para validar que la configuración se carga bien
        val config: Config by inject()
        logger.debug { "Config cargada" }

        RoutesManager.apply {
            app = this@HelloApplication
        }.run {
            initSplashScreen(stage)
        }
    }
}

fun main() {
    Application.launch(HelloApplication::class.java)
}

