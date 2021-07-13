package com.ang.acb.movienight.data.source.remote

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsNull.notNullValue
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class MovieServiceTest {

    // Subject under test
    private lateinit var apiService: MovieService

    // A scriptable web server for testing HTTP clients. Callers supply canned
    // responses and the server replays them upon request in sequence.
    private lateinit var mockServer: MockWebServer

    @Before
    fun createService() {
        mockServer = MockWebServer()

        apiService = Retrofit.Builder()
            .baseUrl(mockServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieService::class.java)
    }

    @After
    fun stopService() {
        mockServer.shutdown()
    }

    @Test
    fun getMovieDetails() {
        // See: https://github.com/Kotlin/kotlinx.coroutines/issues/1204
        runBlocking {
            mockServer.enqueueMockResponse("movie_details_thegodfather.json", 200)
            val response = apiService.getAllMovieDetails(238)

            // Verify that the endpoint contains the movie ID
            val request = mockServer.takeRequest()
            assertThat(request.requestUrl, notNullValue())
            assertThat(request.path, containsString("238"))

            // Verify that the response contains the expected movie details
            val movie = response.asMovie()
            val genres = response.asGenres()
            val cast = response.asCast()
            val trailers = response.asVideos()

            assertThat(movie, notNullValue())
            assertThat(movie.title, `is`("The Godfather"))
            assertThat(movie.releaseDate, `is`("1972-03-14"))
            assertThat(movie.popularity, `is`(31.9))
            assertThat(movie.voteAverage, `is`(8.7))
            assertThat(movie.voteCount, `is`(11450))

            assertThat(genres, notNullValue())
            assertThat(genres.size, `is`(2))
            assertThat(genres[0].name, `is`("Drama"))
            assertThat(genres[1].name, `is`("Crime"))

            assertThat(cast, notNullValue())
            assertThat(cast.size, `is`(59))

            assertThat(trailers, notNullValue())
            assertThat(trailers.size, `is`(2))
        }
    }

    @Test
    fun getCastDetails() {
        runBlocking {
            mockServer.enqueueMockResponse("cast_details_alpacino.json", 200)
            val response = apiService.getCastDetails(1158)

            // Verify that the endpoint contains the cast ID
            val request = mockServer.takeRequest()
            assertThat(request.requestUrl, notNullValue())
            assertThat(request.path, containsString("1158"))

            // Verify that the response contains the expected cast details
            val cast = response.asCastDetails()

            assertThat(cast, notNullValue())
            assertThat(cast.id, `is`(1158))
            assertThat(cast.imdbId, `is`("nm0000199"))
            assertThat(cast.name, `is`("Al Pacino"))
            assertThat(cast.birthday, `is`("1940-04-25"))
        }
    }

    private fun MockWebServer.enqueueMockResponse(fileName: String, code: Int) {
        val inputStream = javaClass.classLoader?.getResourceAsStream("response/$fileName")
        val source = inputStream?.let {
            inputStream.source().buffer()
        }

        source?.let {
            val mockResponse = MockResponse()
                .setResponseCode(code)
                .setBody(source.readString(Charsets.UTF_8))
            enqueue(mockResponse)
        }
    }
}