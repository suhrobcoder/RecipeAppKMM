package uz.suhrob.recipeappkmm.shared.domain.util

interface BaseMapper<Entity, Domain> {
    fun toDomain(model: Entity): Domain

    fun fromDomain(model: Domain): Entity

    fun toDomainList(models: List<Entity>): List<Domain> {
        return models.map { toDomain(it) }
    }

    fun fromDomainList(models: List<Domain>): List<Entity> {
        return models.map { fromDomain(it) }
    }
}