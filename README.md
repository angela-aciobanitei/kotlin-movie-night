# Movie Night

A movies app that fetches data using [The Movie DB API](https://www.themoviedb.org/documentation/api?language=en-US).

## Core Libraries
*   [Compose](https://developer.android.com/jetpack/compose/documentation) for building the UI layer
*   [Accompanist Glide](https://google.github.io/accompanist/glide/) for image loading
*   [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) for data loading
*   [Retrofit 2](https://github.com/square/retrofit) and [OkHttp](https://github.com/square/okhttp) for networking
*   [Gson](https://github.com/google/gson) for parsing JSON
*   [Hilt](https://dagger.dev/hilt/) for dependency injection
  

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
