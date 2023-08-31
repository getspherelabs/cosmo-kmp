package service

import CommonMockWebServer
import CosmoApiTest
import io.ktor.server.testing.testApplication
import response.mockPlanetResponse
import response.mockPlanetsResponse
import kotlin.test.Test
import kotlin.test.assertEquals

class PlanetApiTest : CosmoApiTest() {

    @Test
    fun `when fetch planets and check 200 response`() = testApplication {
        CommonMockWebServer.enqueueMockResponse("/api/v1/planets", mockPlanetsResponse)
        val api = MockPlanetService(httpClient)
        assertEquals(8, api.fetchPlanets().planets.size)
        CommonMockWebServer.verifyGetRequest()
    }

    @Test
    fun `when fetch planet by uuid and check 200 response`() = testApplication {
        CommonMockWebServer.enqueueMockResponse(
            "/api/v1/planet/81b5584a-25ac-11ee-9456-0242ac110177",
            mockPlanetResponse
        )
        val api = MockPlanetService(httpClient)
        val result = api.fetchPlanetById("81b5584a-25ac-11ee-9456-0242ac110177")
        assertEquals("Earth", result.name)
    }
}
