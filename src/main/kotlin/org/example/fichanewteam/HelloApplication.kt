package org.example.fichanewteam

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

class HelloApplication : Application() {
    private lateinit var mainStage: Stage

    override fun start(stage: Stage) {
        try {
            val fxmlLoader = FXMLLoader(HelloApplication::class.java.getResource("/hello-view.fxml"))
            val scene = Scene(fxmlLoader.load(), 1100.0, 750.0)

            // Intentamos cargar el CSS
            val cssUrl = HelloApplication::class.java.getResource("/styles/main.css")
            if (cssUrl != null) {
                scene.stylesheets.add(cssUrl.toExternalForm())
            } else {
                println("No se pudo encontrar el archivo CSS")
            }

            stage.title = "New Team App"
            stage.scene = scene
            stage.isResizable = true
            stage.minWidth = 1000.0
            stage.minHeight = 700.0
            stage.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

fun main() {
    Application.launch(HelloApplication::class.java)
}