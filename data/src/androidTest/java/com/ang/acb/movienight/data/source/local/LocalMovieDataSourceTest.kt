package com.ang.acb.movienight.data.source.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.platform.app.InstrumentationRegistry
import com.ang.acb.movienight.data.utils.MainCoroutineRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@MediumTest
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class LocalMovieDataSourceTest {

    private lateinit var dataSource: LocalMovieDataSource
    private lateinit var database: MoviesDatabase
    private lateinit var dao: MovieDao

    // Swaps the background executor used by the Architecture Components
    // with a different one which executes each task synchronously
    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // Sets the main coroutines dispatcher to a TestCoroutineDispatcher
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun initDb() {
        // Create an in-memory version of the database
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, MoviesDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = database.movieDao

        dataSource = LocalMovieDataSource(
            dao,
            Dispatchers.Main
        )
    }

    @After
    fun closeDb() = database.close()

    @Test
    fun insertMovieAndGetById() {
        mainCoroutineRule.runBlockingTest {
            // Given a new movie that is saved
            val testMovie = FavoriteMovie(
                id = 1L,
                title = "Test title",
                overview = "Test overview",
                releaseDate = "11-11-2020",
                posterPath = null,
                backdropPath = null,
                popularity = 77.7,
                voteAverage = 7.7,
                voteCount = 77,
                isFavorite = true,
            )
            val testId = dataSource.saveFavoriteMovie(testMovie)

            // When the movie is retrieved
            val loadedMovie = dataSource.getFavoriteMovie(testId).first()

            // Then the loaded data contains the expected values
            assertThat(loadedMovie, notNullValue())
            assertThat(loadedMovie, `is`(testMovie))
        }
    }

    @Test
    fun insertContactsAndLoadAll() {
        mainCoroutineRule.runBlockingTest {
            // Given 3 movies that are saved
            val movieA = FavoriteMovie(
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
            val movieB = FavoriteMovie(
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
            val movieC = FavoriteMovie(
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

            dataSource.saveFavoriteMovie(movieA)
            dataSource.saveFavoriteMovie(movieB)
            dataSource.saveFavoriteMovie(movieC)

            // When loading all the movies
            val loaded = dataSource.getAllFavoriteMovies().first()

            // Then the loaded data contains the expected values
            assertThat(loaded, notNullValue())
            assertThat(loaded.size, `is`(3))
        }
    }
}