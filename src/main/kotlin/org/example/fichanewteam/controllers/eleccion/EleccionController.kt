package org.example.fichanewteam.controllers.eleccion

import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.event.EventHandler
import org.example.fichanewteam.routes.RoutesManager


class EleccionController {

    /*
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

     */


    @FXML
    private lateinit var jugadorButton: Button

    @FXML
    private lateinit var entrenadorButton: Button

    fun initialize() {
        initEvents()
    }

    fun initEvents() {
        jugadorButton.setOnAction { onButtonJugadorClicked() }
    }

    fun onButtonJugadorClicked() {
        jugadorButton.setOnAction {
            RoutesManager.initHelpStage()
        }
    }

}