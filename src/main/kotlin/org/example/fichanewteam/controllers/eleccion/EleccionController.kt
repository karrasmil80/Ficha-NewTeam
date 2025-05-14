package org.example.fichanewteam.controllers.eleccion

import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.event.EventHandler


class EleccionController {
    @FXML
    private lateinit var eleccionEntrenador: Button

    @FXML
    private lateinit var eleccionJugador: Button

    @FXML
    private lateinit var textField: TextField

    @FXML
    private fun initialize() {
        initEvents()
    }

    private fun initEvents() {
        eleccionJugador.onMouseClicked = EventHandler { event -> mouseClickJugador() }
        eleccionEntrenador.onMouseClicked = EventHandler { event -> mouseClickEntrenador() }


    }

    private fun mouseClickJugador(): Boolean {
        var isJugador = true
        textField.text = "Jugador"
        return isJugador
    }

    private fun mouseClickEntrenador(): Boolean {
        var isEntrenador = true
        textField.text = "Entrenador"
        return isEntrenador
    }

}