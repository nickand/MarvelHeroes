package com.example.marvelheroes.util

/**
 * Interface to define mapping for domain models
 *
 * More information about what is a ModelMapper here: https://www.baeldung.com/java-modelmapper
 */
interface ModelMapper <T, Model>{

    fun mapToModel(model: T): Model

    fun mapFromModel(domainModel: Model): T

    fun toDomainList(initial: List<T>): List<Model>{
        return initial.map { mapToModel(it) }
    }
}