package org.example.fichanewteam.database

import org.example.fichanewteam.config.Config
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.KotlinPlugin
import org.jdbi.v3.sqlobject.SqlObjectPlugin

import org.lighthousegames.logging.logging


class JdbiManager(
    private val dbUrl: String,
    private val dbInitData: Boolean,
    private val dbInitTables: Boolean
) {
    val logger = logging()
    val jdbi by lazy { Jdbi.create(dbUrl) }

    init {
        jdbi.installPlugin(KotlinPlugin())
        jdbi.installPlugin(SqlObjectPlugin())

        if (dbInitTables) {
            logger.debug { "Cargando Jdbi, creando tablas" }
            executeSqlScriptFromResources("tables.sql")
        }

        if (dbInitData) {
            logger.debug { "Cargando Jdbi, cargando datos" }
            executeSqlScriptFromResources("data.sql")
        }
    }

    private fun executeSqlScriptFromResources(resourcePath: String) {
        val inputStream = ClassLoader.getSystemResourceAsStream(resourcePath)?.bufferedReader()!!
        val script = inputStream.readText()
        jdbi.useHandle<Exception> { handle ->
            handle.createScript(script).execute()
        }
    }


    fun provideDbManager(config: Config): Jdbi{
        return JdbiManager(
            config.dbUrl,
            config.dbInitData,
            config.dbInitTables
        ).jdbi
    }
}