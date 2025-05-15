package org.example.fichanewteam

import javafx.animation.FadeTransition
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.scene.paint.Color
import javafx.stage.Stage
import javafx.stage.StageStyle
import javafx.util.Duration
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
    private lateinit var mainStage: Stage

    override fun start(stage: Stage) {
        logger.debug { "Iniciando New-Team APP" }
        RoutesManager.apply {
            app = this@HelloApplication

        }
        RoutesManager.initSplashScreen(stage)
    }
}

fun main() {
    Application.launch(HelloApplication::class.java)
}

