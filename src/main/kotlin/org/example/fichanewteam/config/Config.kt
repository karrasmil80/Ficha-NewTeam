package org.example.fichanewteam.config


import org.lighthousegames.logging.logging
import java.util.Locale
import java.util.Properties

object Config {
    private val logger = logging()

    var dbUrl: String = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1"
        private set
    var dbInitTables: Boolean = false
        private set
    var dbInitData: Boolean = false
        private set
    var storageData : String = "data"
        private set

    var locale: String = Locale.getDefault().language

    init {
        try {
            logger.debug { "Iniciando configuración" }
            val properties = Properties()
            properties.load(ClassLoader.getSystemResourceAsStream("config.properties"))
            dbUrl = properties.getProperty("database.url", this.dbUrl)
            dbInitTables = properties.getProperty("database.init.tables", this.dbInitTables.toString()).toBoolean()
            dbInitData = properties.getProperty("database.init.data", this.dbInitData.toString()).toBoolean()
            storageData = properties.getProperty("storage.data", this.storageData)
            locale = properties.getProperty("locale", this.locale)
        } catch (e: Exception) {
            logger.error { "Error en el inicio de la cofniguracion: ${e.message}" }
        }
    }
}