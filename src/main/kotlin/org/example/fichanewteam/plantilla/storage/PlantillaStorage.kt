package org.example.fichanewteam.plantilla.storage

import org.example.models.Plantilla
import java.io.File

// Interfaz a implementar en PersonalStorageImpl para poder gestionar los tipos de formato
// Se implementara en el StorageImpl para coordinar instrucciones
interface PlantillaStorage {
    fun readFile(file: File, format: FileFormat ): List<Plantilla>
    fun writeFile(file: File, format: FileFormat, personal: List<Plantilla>) : List<Plantilla>
}