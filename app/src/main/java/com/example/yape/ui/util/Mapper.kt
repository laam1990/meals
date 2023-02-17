package com.example.yape.ui.util

/**
 * Interface for model mappers. It provides helper methods that helps
 * retrieving of models from outer data source layers
 */
interface Mapper<Output, Input> {
    fun executeMapping(type: Input): Output
}
