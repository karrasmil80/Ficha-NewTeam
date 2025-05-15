package org.example.fichanewteam.controllers.loginController

import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.stage.Stage
import org.example.fichanewteam.routes.RoutesManager
import org.example.fichanewteam.utils.PasswordUtils
import org.example.fichanewteam.utils.PasswordUtils.checkPassword
import org.lighthousegames.logging.logging
import org.mindrot.jbcrypt.BCrypt

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

    private val usuarios = mutableMapOf<String, String>(
        "Pablo" to BCrypt.hashpw("admin", BCrypt.gensalt()),
        "Lucia" to BCrypt.hashpw("user", BCrypt.gensalt())
    )


    fun initialize() {
        logger.debug { "Iniciando pantalla de login" }
        initEvents()
    }

    fun initEvents(){
        loginButton.setOnAction {
            val username = usuarioField.text
            val password = contrasenaField.text
            onLoginAction(username, password)
        }
    }



    fun onLoginAction(userName : String, password: String){
        val passwordHashed = usuarios[userName]

        if (passwordHashed == null) {
            contrasenaField.clear()
            usuarioField.clear()
        }

        val loginCorrecto = BCrypt.checkpw(password, passwordHashed)

        if (loginCorrecto) {
            RoutesManager.initPlantillaStage()
        } else {
            Alert(Alert.AlertType.ERROR).apply {
                title = "Contraseña o usuario incorrecto"
                headerText = "Vuelva a introducir sus datos correctamente"

            }
        }

    }






}