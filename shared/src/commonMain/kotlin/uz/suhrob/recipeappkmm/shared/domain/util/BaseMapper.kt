package uz.suhrob.recipeappkmm.shared.domain.util

interface BaseMapper<Entity, Domain> {
    fun toDomain(model: Entity): Domain

    fun fromDomain(model: Domain): Entity
}