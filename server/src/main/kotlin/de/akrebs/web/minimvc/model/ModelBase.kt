package de.akrebs.web.minimvc.model

import io.quarkus.hibernate.reactive.panache.PanacheEntity

/**
 * Extend PanacheEntity for customization.
 */
abstract class ModelBase : PanacheEntity() {

    companion object NullModel : ModelBase() {
        override fun isValid() : Boolean {
            return true
        }
    }

    val constraints: List<Function<Boolean>> = emptyList()

    fun notNull(obj: Any) : Boolean {
        return obj != null
    }

    fun validate() {
        constraints.forEach(){
            f -> f.apply {  }
        }
    }

    abstract fun isValid() : Boolean
}