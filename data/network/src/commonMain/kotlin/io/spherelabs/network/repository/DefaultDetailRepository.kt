package io.spherelabs.network.repository

import io.spherelabs.detaildomain.ArticleDetailDomain
import io.spherelabs.detaildomain.DetailRepository
import io.spherelabs.detaildomain.FavouriteDetailDomain
import io.spherelabs.detaildomain.PlanetDetailDomain
import io.spherelabs.detaildomain.StarDetailDomain
import io.spherelabs.local.db.FavouriteDao
import io.spherelabs.local.mapper.asEntity
import io.spherelabs.network.dto.asDetailDomain
import io.spherelabs.network.service.ArticleService
import io.spherelabs.network.service.PlanetService
import io.spherelabs.network.service.StarService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class DefaultDetailRepository(
    private val articleService: ArticleService,
    private val planetService: PlanetService,
    private val starService: StarService,
    private val favouriteDao: FavouriteDao
) : DetailRepository {

    override suspend fun fetchStarByUuid(uuid: String): StarDetailDomain {
        return starService.fetchStarByUuid(uuid).asDetailDomain()
    }

    override suspend fun fetchArticleByUuid(uuid: String): ArticleDetailDomain {
        return articleService.fetchArticleByUuid(uuid).asDetailDomain()
    }

    override suspend fun insertFavourite(favourite: FavouriteDetailDomain) {
        favouriteDao.insertFavourite(favourite.asEntity())
    }

    override suspend fun fetchPlanetById(id: String): PlanetDetailDomain {
        return planetService.fetchPlanetById(id).asDetailDomain()
    }

    override suspend fun deleteFavouriteByUuid(id: String) {
        withContext(Dispatchers.IO) {
            favouriteDao.deleteFavourite(id)
        }
    }

    override suspend fun isFavourite(id: String): Boolean {
        return withContext(Dispatchers.IO) {
            favouriteDao.isFavourite(id)
        }
    }
}
