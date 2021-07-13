package com.ang.acb.movienight.data.source.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@SmallTest
@RunWith(AndroidJUnit4::class)
class MovieDaoTest {
    private lateinit var database: MoviesDatabase
    private lateinit var movieDao: MovieDao

    private val movieA = FavoriteMovie(
        id = 1L,
        title = "A",
        overview = "overview A",
        releaseDate = "11-11-2020",
        posterPath = null,
        backdropPath = null,
        popularity = 77.7,
        voteAverage = 7.7,
        voteCount = 77,
        isFavorite = true,
    )
    private val movieB = FavoriteMovie(
        id = 2L,
        title = "B",
        overview = "overview B",
        releaseDate = "12-11-2020",
        posterPath = null,
        backdropPath = null,
        popularity = 88.8,
        voteAverage = 8.8,
        voteCount = 88,
        isFavorite = true,
    )
    private val movieC = FavoriteMovie(
        id = 3L,
        title = "C",
        overview = "overview C",
        releaseDate = "10-11-2020",
        posterPath = null,
        backdropPath = null,
        popularity = 99.9,
        voteAverage = 9.9,
        voteCount = 99,
        isFavorite = true,
    )

    // Swaps the background executor used by the Architecture Components
    // with a different one which executes each task synchronously
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        runBlocking {
            // Create an in-memory version of the database
            val context = InstrumentationRegistry.getInstrumentation().targetContext
            database = Room.inMemoryDatabaseBuilder(context, MoviesDatabase::class.java).build()
            movieDao = database.movieDao

            // Insert movies in non-alphabetical order to test that results are sorted by title
            movieDao.insertMovie(movieB)
            movieDao.insertMovie(movieA)
            movieDao.insertMovie(movieC)
        }
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testGetFavoriteMovies() {
        runBlocking {
            val movies = movieDao.getAllFavoriteMovies().first()
            assertThat(movies.size, `is`(3))

            // Ensure list is sorted by title
            assertThat(movies[0], `is`(movieA))
            assertThat(movies[1], `is`(movieB))
            assertThat(movies[2], `is`(movieC))
        }
    }

    @Test
    fun testGetFavoriteMovie() {
        runBlocking {
            assertThat(movieDao.getMovie(movieA.id).first(), notNullValue())
            assertThat(movieDao.getMovie(movieA.id).first(), `is`(movieA))
        }
    }
}