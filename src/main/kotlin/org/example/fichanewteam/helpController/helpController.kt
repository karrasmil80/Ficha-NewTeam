package org.example.fichanewteam.helpController

import javafx.fxml.FXML
import org.lighthousegames.logging.logging

private val logger = logging()
class helpController {
    @FXML
    private lateinit var urlGithub : javafx.scene.control.Hyperlink

    @FXML
    fun initialize() {
        logger.debug { "Inicializando HelpController" }
        urlGithub.setOnAction {
            logger.debug { "Redirigiendo a GitHub" }
            val url1 = "https://github.com/"
        }

    }
}