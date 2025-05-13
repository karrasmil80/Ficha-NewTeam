package org.example.fichanewteam.plantilla.repositories

import org.example.fichanewteam.plantilla.models.Plantilla


interface PlantillaRepository<T>: CrudRepository<Plantilla, Long> {

    // aqui se añadiran distintas consultas
}
