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

/*
class HelloApplication : Application() {
    private lateinit var mainStage: Stage

    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(HelloApplication::class.java.getResource("plantilla-view.fxml"))
        val scene = Scene(fxmlLoader.load(), 320.0, 240.0)
        stage.title = "Hello!"
        stage.scene = scene
        stage.show()
    }
}

fun main() {
    Application.launch(HelloApplication::class.java)
}*/

class HelloApplication : Application() {
    private lateinit var mainStage: Stage

    override fun start(primaryStage: Stage) {
        mainStage = primaryStage

        // Cargar la SplashScreen
        val splashLoader = FXMLLoader(HelloApplication::class.java.getResource("splash.fxml"))
        val splashPane = splashLoader.load<javafx.scene.layout.StackPane>()
        val splashScene = Scene(splashPane)
        splashScene.fill = Color.TRANSPARENT

        // Configurar el Stage para la SplashScreen
        val splashStage = Stage()
        splashStage.initStyle(StageStyle.TRANSPARENT)
        splashStage.scene = splashScene
        splashStage.icons.add(Image(HelloApplication::class.java.getResourceAsStream("/images/logo.png")))

        // Mostrar la SplashScreen
        splashStage.show()

        // Crear una transición de desvanecimiento
        val fadeOut = FadeTransition(Duration.seconds(3.0), splashPane)
        fadeOut.fromValue = 1.0
        fadeOut.toValue = 0.0
        fadeOut.setOnFinished {
            splashStage.close()
            mostrarVentanaPrincipal()
        }

        // Iniciar la transición después de un breve retraso
        fadeOut.play()
    }

    private fun mostrarVentanaPrincipal() {
        try {
            // Cargar la ventana principal
            val mainLoader = FXMLLoader(HelloApplication::class.java.getResource("/plantilla-view.fxml"))
            val mainPane = mainLoader.load<javafx.scene.Parent>()
            val mainScene = Scene(mainPane)

            // Configurar el Stage principal
            mainStage.title = "New Team App"
            mainStage.scene = mainScene
            mainStage.icons.add(Image(HelloApplication::class.java.getResourceAsStream("/images/logo.png")))
            mainStage.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(HelloApplication::class.java)
        }
    }
}