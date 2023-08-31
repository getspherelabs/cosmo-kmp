
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.HttpRequestData
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.fullPath
import io.ktor.http.headersOf
import response.MockResponse
import kotlin.properties.Delegates
import kotlin.test.assertEquals

object CommonMockWebServer {

    private var mockResponse: MockResponse by Delegates.notNull()
    private var lastRequestData: HttpRequestData by Delegates.notNull()

    fun enqueueMockResponse(
        endpoint: String,
        body: String,
        statusCode: Int = 200
    ) {
        mockResponse = MockResponse(endpoint, body, statusCode)
    }

    fun get() = MockEngine.create {
        addHandler { request: HttpRequestData ->
            lastRequestData = request

            when (request.url.encodedPath) {
                mockResponse.endPoint -> {
                    respond(
                        content = mockResponse.responseBody,
                        status = HttpStatusCode.fromValue(mockResponse.statusCode),
                        headers = headersOf(HttpHeaders.ContentType, "application/json")
                    )
                }
                else -> {
                    error("Unhandled ${mockResponse.endPoint}  -  ${request.url.fullPath}")
                }
            }
        }
    }

    fun verifyGetRequest() {
        assertEquals(HttpMethod.Get.value, lastRequestData.method.value)
    }
}
