import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import io.spherelabs.local.db.CosmoDatabase

actual class TestSqlDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(
            CosmoDatabase.Schema,
            "cosmo_test.db"
        )
    }
}
