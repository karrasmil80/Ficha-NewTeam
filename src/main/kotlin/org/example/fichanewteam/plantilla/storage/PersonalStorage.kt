package org.example.fichanewteam.plantilla.storage

import org.example.fichanewteam.plantilla.models.Plantilla
import java.io.File
//PARTE BUENA
interface PersonalStorage {
    fun readFile(file: File, format: FileFormat): List<Plantilla>
    fun writeFile(file: File, format: FileFormat, personal: List<Plantilla>)
}
//PARTE BUENA