package org.example.fichanewteam

import com.github.benmanes.caffeine.cache.Cache
import org.example.fichanewteam.plantilla.dao.PlantillaDao
import org.example.fichanewteam.plantilla.mapper.toEntity
import org.example.fichanewteam.plantilla.models.Entrenador
import org.example.fichanewteam.plantilla.models.Plantilla
import org.example.fichanewteam.plantilla.repositories.PlantillaRepository
import org.example.fichanewteam.plantilla.repositories.PlantillaRepositoryImpl
import org.example.fichanewteam.plantilla.service.PlantillaService
import org.example.fichanewteam.plantilla.service.PlantillaServiceImpl
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
class TestPrueba {

    @Mock
    private lateinit var repository: PlantillaRepositoryImpl

    @Mock
    private lateinit var cache: Cache<Long, Plantilla>

    @InjectMocks
    private lateinit var service: PlantillaServiceImpl

    private val miembro = Entrenador(1L, "Juan", "Garcia", "14/12/2014", "20/3/2020", 50.00, "España", "Entrenador", "ENTRENADOR_ASISTENTE", rutaImagen = "images/default_profile.png")


    @Test
    @DisplayName("Encuentra al miembro en la cache")
    fun findBtIdFromCache() {
        whenever(cache.getIfPresent(entrenador.id)).thenReturn(miembro)
    }

}