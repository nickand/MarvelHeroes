package com.example.memefon.util

/**
 * Interface to define mapping for domain models
 *
 * More information about what is a ModelMapper here: https://www.baeldung.com/java-modelmapper
 */
interface ModelMapper <T, Model>{

    fun mapToModel(model: T): Model

    fun toDomainList(initial: T): Model {
        return mapToModel(initial)
    }
}