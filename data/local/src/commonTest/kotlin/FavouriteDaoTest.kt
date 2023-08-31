
import app.cash.sqldelight.db.SqlDriver
import app.cash.turbine.test
import io.spherelabs.local.db.CosmoDatabase
import io.spherelabs.local.db.DefaultFavouriteDao
import io.spherelabs.local.db.FavouriteDao
import iospherelabslocaldb.Favourite
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class FavouriteDaoTest {

    private lateinit var sqlDriverFactory: SqlDriver
    private lateinit var database: CosmoDatabase
    private lateinit var dao: FavouriteDao

    @BeforeTest
    fun setup() {
        sqlDriverFactory = TestSqlDriverFactory().createDriver()
        database = CosmoDatabase.invoke(sqlDriverFactory)
        dao = DefaultFavouriteDao(database)
    }

    @Test
    fun `check insert and get favourites`() = runTest {
        val favourite = Favourite(
            id = "1",
            name = "Mars",
            description = "Mars",
            size = "Big",
            distanceFromSun = "175 million km",
            isFavourite = 0,
            createdTimestamp = "2023"
        )
        dao.insertFavourite(favourite)

        val result = dao.getFavourites()
        result.test {
            assertEquals(1, awaitItem().size)
        }
    }

    @Test
    fun `check insert and get favourite item`() = runTest {
        val favourite = Favourite(
            id = "1",
            name = "Mars",
            description = "Mars",
            size = "Big",
            distanceFromSun = "175 million km",
            isFavourite = 1,
            createdTimestamp = "2023"
        )
        dao.insertFavourite(favourite)

        val result = dao.isFavourite("1")

        assertEquals(true, result)
    }
}
