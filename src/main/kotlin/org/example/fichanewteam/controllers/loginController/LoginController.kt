package org.example.fichanewteam.controllers.loginController

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.control.ToggleButton
import javafx.stage.Stage
import org.example.fichanewteam.routes.RoutesManager
import org.lighthousegames.logging.logging

private val logger = logging()
class LoginController {

    @FXML
    private lateinit var mensajeLabel: Label
    @FXML
    private lateinit var loginButton: ToggleButton
    @FXML
    private lateinit var contrasenaField: PasswordField
    @FXML
    private lateinit var usuarioField: TextField

    fun initialize() {
        logger.debug { "Iniciando pantalla de login" }
        loginButton.setOnAction {
            RoutesManager.initPlantillaStage()
        }
    }
}