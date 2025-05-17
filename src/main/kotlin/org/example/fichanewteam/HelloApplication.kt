package org.example.fichanewteam

import javafx.application.Application
import javafx.stage.Stage
import org.example.fichanewteam.di.appModule
import org.example.fichanewteam.routes.RoutesManager
import org.koin.core.context.startKoin
import org.lighthousegames.logging.logging

private val logger = logging()
class HelloApplication : Application() {

    init {
        startKoin {
            printLogger()
            modules(appModule)
        }
    }

    override fun start(stage: Stage) {
        logger.debug { "Iniciando New-Team APP" }

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

