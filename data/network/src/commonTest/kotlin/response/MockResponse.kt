package response

data class MockResponse(
    val endPoint: String,
    val responseBody: String,
    val statusCode: Int = 200
)
