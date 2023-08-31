package io.spherelabs.detaildomain

interface DetailRepository {
    suspend fun fetchPlanetById(id: String): PlanetDetailDomain
    suspend fun fetchStarByUuid(uuid: String): StarDetailDomain
    suspend fun fetchArticleByUuid(uuid: String): ArticleDetailDomain
    suspend fun insertFavourite(favourite: FavouriteDetailDomain)
    suspend fun deleteFavouriteByUuid(id: String)
    suspend fun isFavourite(id: String): Boolean
}
