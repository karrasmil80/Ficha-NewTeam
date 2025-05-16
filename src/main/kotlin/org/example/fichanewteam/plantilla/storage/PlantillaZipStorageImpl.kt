package org.example.fichanewteam.plantilla.storage

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.example.fichanewteam.config.Config
import org.example.fichanewteam.plantilla.error.PlantillaError
import org.example.fichanewteam.plantilla.models.Plantilla
import org.lighthousegames.logging.logging
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import java.util.zip.ZipOutputStream
import kotlin.io.path.createTempDirectory
import kotlin.io.path.name

private val logger = logging()

class PlantillaZipStorageImpl(
    private val config: Config,
    private val jsonStorage: PlantillaStorageJson
) : PlantillaZipStorage {

    val tempDir = "miembrosPlantilla"
    override fun exportToZip(zipFile: File, data: List<Plantilla>): Result<File, PlantillaError> {
        logger.debug { "Exportando a ZIP $zipFile" }
        val tempDir = Files.createTempDirectory(tempDir)
        return try {

            data.forEach {
                val file = File(config.imagesDirectory + it.rutaImagen)
                if (file.exists()) {
                    Files.copy(
                        file.toPath(),
                        Paths.get(tempDir.toString(), file.name),
                        StandardCopyOption.REPLACE_EXISTING
                    )
                }
            }
            jsonStorage.storageDataJson(File("$tempDir/data.json"), data)
            Files.walk(tempDir).forEach { logger.debug { it } }
            val archivos = Files.walk(tempDir)
                .filter { Files.isRegularFile(it) }
                .toList()
            ZipOutputStream(Files.newOutputStream(zipFile.toPath())).use { zip ->
                archivos.forEach { archivo ->
                    val entradaZip = ZipEntry(tempDir.relativize(archivo).toString())
                    zip.putNextEntry(entradaZip)
                    Files.copy(archivo, zip)
                    zip.closeEntry()
                }
            }
            tempDir.toFile().deleteRecursively()
            Ok(zipFile)
        } catch (e: Exception) {
            logger.error { "Error al exportar a ZIP: ${e.message}" }
            Err(PlantillaError.PlantillaStorageError("Error al exportar a ZIP: ${e.message}"))
        }
    }

    override fun loadFromZip(unzipFile: File): Result<List<Plantilla>, PlantillaError> {
        logger.debug { "Importando desde ZIP $unzipFile" }
        val tempDir = Files.createTempDirectory(tempDir)
        return try {
            ZipFile(unzipFile).use { zip ->
                zip.entries().asSequence().forEach { entrada ->
                    zip.getInputStream(entrada).use { input ->
                        Files.copy(
                            input,
                            Paths.get(tempDir.toString(), entrada.name),
                            StandardCopyOption.REPLACE_EXISTING
                        )
                    }
                }
            }
            Files.walk(tempDir).forEach {
                // copiamos todas las imagenes, es decir, todo lo que no es .json al directorio de imagenes
                if (!it.toString().endsWith(".json") && Files.isRegularFile(it)) {
                    Files.copy(
                        it,
                        Paths.get(config.imagesDirectory, it.name),
                        StandardCopyOption.REPLACE_EXISTING
                    )
                }
            }
            val data = jsonStorage.loadDataJson(File("$tempDir/data.json"))
            tempDir.toFile().deleteRecursively()
            return data
        } catch (e: Exception) {
            logger.error { "Error al importar desde ZIP: ${e.message}" }
            Err(PlantillaError.PlantillaStorageError("Error al importar desde ZIP: ${e.message}"))
        }
    }
}

