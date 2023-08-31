package io.spherelabs.aboutdomain.di

import io.spherelabs.aboutdomain.DefaultGetAboutById
import io.spherelabs.aboutdomain.DefaultInsertAbout
import io.spherelabs.aboutdomain.DefaultUpdateAbout
import io.spherelabs.aboutdomain.GetAboutById
import io.spherelabs.aboutdomain.InsertAbout
import io.spherelabs.aboutdomain.UpdateAbout
import org.koin.dsl.module

val aboutDomainModule = module {
    single<InsertAbout> { DefaultInsertAbout(get()) }
    single<UpdateAbout> { DefaultUpdateAbout(get()) }
    single<GetAboutById> { DefaultGetAboutById(get()) }
}
