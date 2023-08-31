package io.spherelabs.network.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import io.spherelabs.detaildomain.DetailRepository
import io.spherelabs.discoverdomain.DiscoverRepository
import io.spherelabs.network.repository.DefaultDetailRepository
import io.spherelabs.network.repository.DefaultDiscoverRepository
import io.spherelabs.network.repository.DefaultSearchRepository
import io.spherelabs.network.repository.DefaultStarsRepository
import io.spherelabs.network.service.ArticleService
import io.spherelabs.network.service.DefaultArticleService
import io.spherelabs.network.service.DefaultPlanetService
import io.spherelabs.network.service.DefaultStarService
import io.spherelabs.network.service.PlanetService
import io.spherelabs.network.service.StarService
import io.spherelabs.searchdomain.SearchRepository
import io.spherelabs.starsdomain.repository.StarsRepository
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val serviceModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                        isLenient = true
                    }
                )
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
    }

    single<PlanetService> { DefaultPlanetService(get()) }
    single<StarService> { DefaultStarService(get()) }
    single<ArticleService> { DefaultArticleService(get()) }
    single<DiscoverRepository> { DefaultDiscoverRepository(get(), get()) }
    single<StarsRepository> { DefaultStarsRepository(get()) }
    single<SearchRepository> { DefaultSearchRepository(get()) }
    single<DetailRepository> { DefaultDetailRepository(get(), get(), get(), get()) }
}
