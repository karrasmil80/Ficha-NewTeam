package org.example.fichanewteam.error

sealed class PlantillaError(msg:String) {
    class PlantillaValidatorError(msg:String) : PlantillaError(msg)
    class PlantillaIdNotFound(msg:String) : PlantillaError(msg)
    class PlantillaInvalidId(msg:String) : PlantillaError(msg)
    class PlantillaStorageError(msg:String) : PlantillaError(msg)
}