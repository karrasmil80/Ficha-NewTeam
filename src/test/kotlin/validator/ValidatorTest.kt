package validator

import org.example.fichanewteam.plantilla.dao.PlantillaEntity
import org.example.fichanewteam.plantilla.error.PlantillaError
import org.example.fichanewteam.plantilla.mapper.toModel
import org.example.fichanewteam.plantilla.models.Entrenador
import org.example.fichanewteam.plantilla.models.Plantilla
import org.example.fichanewteam.plantilla.validator.PlantillaValidator
import org.example.fichanewteam.plantilla.validator.Validator
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.kotlin.whenever

class ValidatorTest {

    private lateinit var validator: Validator<Plantilla, PlantillaError>

    @BeforeEach
    fun setUp() {
        validator = PlantillaValidator()
    }

    @Nested
    @DisplayName("Casos correctos")
    inner class CasosCorrectos {

        @Test
        @DisplayName("Deberia devolver un miembro de la plantilla, totalmente valido")
        fun plantillaOk() {
            val plantilla = PlantillaEntity(
                id = 1,
                nombre = "Pepe",
                apellidos = "Gutierrez",
                fechaNacimiento = "1970-01-01",
                fechaIncorporacion = "1990-01-01",
                salario = 1000.0,
                pais = "España",
                rol = Entrenador.Especializacion.ENTRENADOR_PORTEROS.toString(),
                rutaImagen = ""
            )

            val modelo = plantilla.toModel()

            val plantillaTrue = validator.validate(modelo)

            assertTrue(plantillaTrue.isOk)
        }
    }

    @Nested
    @DisplayName("Casos incorrectos")
    inner class CasosIncorrectos {
        @Test
        @DisplayName("Deberia de devolver un miembro de la plantilla invalido")
        fun VehiculoNotOK() {
            val plantilla = PlantillaEntity(
                id = 0L,
                nombre = "Pepe",
                apellidos = "Gutierrez",
                fechaNacimiento = "1970-01-01",
                fechaIncorporacion = "1990-01-01",
                salario = 1000.0,
                pais = "España",
                rol = Entrenador.Especializacion.ENTRENADOR_PORTEROS.toString(),
                rutaImagen = ""
            )

            val modelo = plantilla.toModel()

            val plantillaInvalida = validator.validate(modelo)

            assertTrue(plantillaInvalida.isOk)
        }
    }


}