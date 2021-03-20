package uz.suhrob.recipeappkmm.shared.data.network

import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import uz.suhrob.recipeappkmm.shared.data.network.model.RecipeDto
import uz.suhrob.recipeappkmm.shared.data.network.model.RecipeSearchItemDto
import uz.suhrob.recipeappkmm.shared.data.network.response.RandomRecipeResponse
import uz.suhrob.recipeappkmm.shared.data.network.response.RecipeSearchResponse

class RecipeApi {
    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }
        install(DefaultRequest) {
            parameter("apiKey", API_KEY)
            headers.append("Content-Type", "application/json")
        }
    }

    suspend fun getRecipeById(
        id: Int
    ): RecipeDto {
        return client.get { url("${BASE_URL}recipes/$id/information") }
    }

    suspend fun searchRecipes(
        query: String,
        cuisine: List<String>,
        diet: String,
        includeIngredients: List<String>,
        maxReadyTime: Int,
        sort: String,
        sortDirection: String,
        number: Int,
        offset: Int,
    ): RecipeSearchResponse {
        return client.get {
            url("${BASE_URL}/recipes/complexSearch")
            parameter("query", query)
            parameter("cuisine", cuisine.joinToString(separator = ", "))
            parameter("diet", diet)
            parameter("includeIngredients", includeIngredients.joinToString(separator = ", "))
            parameter("maxReadyTime", maxReadyTime)
            parameter("sort", sort)
            parameter("sortDirection", sortDirection)
            parameter("number", number)
            parameter("offset", offset)
        }
    }

    suspend fun getRandomRecipes(
        number: Int,
        tags: List<String> = listOf(),
        offset: Int,
    ): RandomRecipeResponse {
        return client.get {
            url("${BASE_URL}/recipes/random")
            parameter("number", number)
            parameter("tags", tags.joinToString(separator = ", "))
            parameter("offset", offset)
        }
    }

    suspend fun autoCompleteRecipeSearch(
        query: String,
        number: Int,
    ): List<RecipeSearchItemDto> {
        return client.get {
            url("$BASE_URL/recipes/autocomplete")
            parameter("query", query)
            parameter("number", number)
        }
    }

    companion object {
        const val BASE_URL = "https://api.spoonacular.com/"
        const val API_KEY = "fecbc99e62c542b99f8e9f2532bf959d"
    }
}