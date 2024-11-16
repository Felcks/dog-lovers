package com.matheus.doglovers.dogs.domain.models

interface Breed {
    val name: String
    val specification: String?

    fun getVisualizationName(): String = specification?.let {
        "$it $name"
    } ?: name

    fun getResourceName(): String = specification?.let {
        "$name/$it"
    } ?: name
}
