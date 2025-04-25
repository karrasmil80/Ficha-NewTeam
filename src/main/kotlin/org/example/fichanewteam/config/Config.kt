package org.example.fichanewteam.config

import org.lighthousegames.logging.logging
import java.util.Locale
import java.util.Properties


object Config {
    val logger = logging()
    var locale = Locale.getDefault().toString()
    var dbUrl = "jdbc:h2:tcp://localhost:9092/default"
        private set
    var dbInitTables = false
        private set
    var dbInitData = false
        private set
    var storageData = "data"
        private set

    init {
        try {
            val properties = Properties()
            properties.load(ClassLoader.getSystemResourceAsStream("config.properties"))
            locale = properties.getProperty("locale", this.locale)
            dbUrl = properties.getProperty("dbUrl", this.dbUrl)
            dbInitTables = properties.getProperty("dbInitTables", this.dbInitTables.toString()).toBoolean()
            dbInitData = properties.getProperty("dbInitData", this.dbInitData.toString()).toBoolean()
            storageData = properties.getProperty("storageData", this.storageData)
            logger.debug { "Cargando la configuracion" }
        }catch(e:Exception){
            logger.error { "Fallo cargando la configuracion" }
        }
    }
}