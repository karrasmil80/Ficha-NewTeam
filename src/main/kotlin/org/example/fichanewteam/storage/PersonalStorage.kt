package org.example.fichanewteam.storage

import org.example.models.Plantilla
import java.io.File

interface PersonalStorage {
    fun readFile(file: File, format: FileFormat ): List<Plantilla>
    fun writeFile(file: File, format: FileFormat, personal: List<Plantilla>)
}