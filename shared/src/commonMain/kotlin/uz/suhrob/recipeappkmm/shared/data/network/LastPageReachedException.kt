package uz.suhrob.recipeappkmm.shared.data.network

private const val LAST_PAGE_REACHED_EXCEPTION = "Last page reached"

class LastPageReachedException : Exception(LAST_PAGE_REACHED_EXCEPTION)