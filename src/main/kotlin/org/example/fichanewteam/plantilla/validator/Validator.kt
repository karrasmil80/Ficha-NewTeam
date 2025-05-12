package org.example.fichanewteam.plantilla.validator

import com.github.michaelbull.result.Result
//PARTE BUENA
interface Validator<T, E> {
    fun validate (t: T): Result<T, E>
}
//PARTE BUENA