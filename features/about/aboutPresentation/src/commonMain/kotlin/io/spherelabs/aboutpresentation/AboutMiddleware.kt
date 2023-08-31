package io.spherelabs.aboutpresentation

import io.spherelabs.aboutdomain.GetAboutById
import io.spherelabs.aboutdomain.InsertAbout
import io.spherelabs.aboutdomain.UpdateAbout
import io.spherelabs.meteor.middleware.Middleware
import kotlinx.coroutines.flow.collectLatest

class AboutMiddleware(
    private val insertAbout: InsertAbout,
    private val updateAbout: UpdateAbout,
    private val getAboutById: GetAboutById
) : Middleware<AboutState, AboutWish> {

    override suspend fun process(
        state: AboutState,
        wish: AboutWish,
        next: suspend (AboutWish) -> Unit
    ) {
        when (wish) {
            is AboutWish.GetAboutById -> {
                getAboutById.execute(wish.id).collectLatest { result ->
                    result.onSuccess { newAbout ->
                        next.invoke(AboutWish.GetAboutMe(newAbout))
                    }.onFailure {
                        val failureMessage = it.message ?: "Error is occurred."
                        next.invoke(AboutWish.GetAboutFailed(failureMessage))
                    }
                }
            }
            is AboutWish.InsertAbout -> {
                runCatching {
                    insertAbout.execute(wish.about)
                }.onSuccess {
                    next.invoke(AboutWish.Inserted("Added successfully"))
                }.onFailure {
                    val failureMessage = it.message ?: "Error is occurred."
                    next.invoke(AboutWish.InsertFailed(failureMessage))
                }
            }
            is AboutWish.UpdateAbout -> {
                runCatching {
                    updateAbout.execute(wish.about)
                }.onSuccess {
                    next.invoke(AboutWish.Updated("Added successfully"))
                }.onFailure {
                    val failureMessage = it.message ?: "Error is occurred."
                    next.invoke(AboutWish.UpdateFailed(failureMessage))
                }
            }
            else -> {}
        }
    }
}
