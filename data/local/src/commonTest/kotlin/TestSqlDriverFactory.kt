import app.cash.sqldelight.db.SqlDriver

expect class TestSqlDriverFactory() {
    fun createDriver(): SqlDriver
}
