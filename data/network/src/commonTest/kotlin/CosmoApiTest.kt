
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlin.test.BeforeTest

open class CosmoApiTest {
    lateinit var httpClient: HttpClient

    @BeforeTest
    fun setup() {
        httpClient = HttpClient(CommonMockWebServer.get()) {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                        isLenient = true
                        encodeDefaults = true
                    }
                )
            }
        }
    }
}
