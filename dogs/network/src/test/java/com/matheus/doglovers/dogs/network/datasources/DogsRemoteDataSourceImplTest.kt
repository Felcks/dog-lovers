package com.matheus.doglovers.dogs.network.datasources

import app.cash.turbine.test
import com.matheus.doglovers.core.domain.wrapper.Resource
import com.matheus.doglovers.dogs.network.api.DogsService
import com.matheus.doglovers.dogs.network.models.BreedResponse
import com.matheus.doglovers.dogs.network.models.BreedsResponse
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class DogsRemoteDataSourceImplTest {

    private lateinit var mockService: DogsService
    private lateinit var sut: DogsRemoteDataSourceImpl

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        mockService = mockk()
        sut = DogsRemoteDataSourceImpl(mockService, UnconfinedTestDispatcher())
    }

    @Test
    fun `GIVEN service returns correctly WHEN getBreeds THEN returns breeds correctly`() = runTest {
        val expected = listOf<BreedResponse>(
            BreedResponse("a", null),
            BreedResponse("b", "1"),
            BreedResponse("b", "2")
        )

        val serviceResponse = BreedsResponse(
            message = mapOf(
                "a" to listOf(),
                "b" to listOf("1", "2")
            )
        )
        coEvery { mockService.getBreeds() } returns mockk() {
            every { body() } returns serviceResponse
        }

        val result = sut.getBreeds()

        result.test {
            awaitItem()
            assertEquals(expected, (awaitItem() as Resource.Success).data)
            awaitComplete()
        }
    }

    @Test
    fun `GIVEN service returns error WHEN getBreeds THEN returns error`() = runTest {
        coEvery { mockService.getBreeds() } throws Exception()

        val result = sut.getBreeds()

        result.test {
            awaitItem()
            assertThat(awaitItem(), instanceOf(Resource.Error::class.java))
            awaitComplete()
        }
    }
}