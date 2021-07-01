# Movie Night

[![codebeat badge](https://codebeat.co/badges/7fac844a-b554-4704-a686-7195c889e845)](https://codebeat.co/projects/github-com-angela-aciobanitei-kotlin-movie-night-master)

A movies app that fetches data using [TMDB](https://www.themoviedb.org/documentation/api?language=en-US), allowing users to filter the movies by most popular, top rated, or similar criteria. Users can also search for a movie, find more details about a movie, or mark their favourite movies.

The app uses clean architecture, MVVM, Kotlin coroutines, Flow and the latest Jetpack libraries, including Compose.

## Core Libraries
*   [Hilt](https://dagger.dev/hilt/) for dependency injection
*   [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) and [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/) for asynchronous programming
*   [Compose](https://developer.android.com/jetpack/compose/documentation) for building the UI layer
*   [Navigation](https://developer.android.com/jetpack/compose/navigation) to navigate between composables
*   [Accompanist Glide](https://google.github.io/accompanist/glide/) for image loading
*   [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) for data loading
*   [Retrofit 2](https://github.com/square/retrofit) and [OkHttp](https://github.com/square/okhttp) for networking
*   [Gson](https://github.com/google/gson) for parsing JSON
*   [Room](https://developer.android.com/topic/libraries/architecture/room) for data persistence 
  

## Installing the App

*   Clone this repository
    ```
    git https://github.com/angela-aciobanitei/kotlin-movie-night.git
    ```
*   Go to [The Movie Database](https://developers.themoviedb.org/3/getting-started/introduction) page and register for an API key.
*   Import the project in Android Studio and add the TMDB API Key inside the `gradle.properties` file.

    ```
    TMDB_API_KEY="Your API Key Here"
    ```
* Note: since the project uses Jetpack Compose, you need to use the latest [Android Studio Arctic Fox](https://developer.android.com/studio/preview) release in order to be able to build the app.

## Demo

https://user-images.githubusercontent.com/37955938/124139986-046f3c80-da80-11eb-8cb7-51fece9b9362.mp4

https://user-images.githubusercontent.com/37955938/124140010-0a651d80-da80-11eb-979c-311fdb499fad.mp4
