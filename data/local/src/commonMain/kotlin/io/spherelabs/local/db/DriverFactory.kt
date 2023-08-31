package io.spherelabs.local.db

import app.cash.sqldelight.db.SqlDriver

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun createDatabase(driver: DriverFactory): CosmoDatabase {
    return CosmoDatabase.invoke(
        driver.createDriver()
    )
}
