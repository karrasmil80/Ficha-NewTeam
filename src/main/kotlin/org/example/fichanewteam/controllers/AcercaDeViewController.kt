package org.example.fichanewteam.controllers

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.stage.Stage

class AcercaDeController {

    @FXML
    private fun handleClose() {
        // Obtener la ventana actual y cerrarla
        val stage = null //(javafx.scene.control.Button::class.java.getResource("/views/acerca-de-view.fxml") as? Button)?.scene?.window as? Stage
        //stage?.close()
    }
}