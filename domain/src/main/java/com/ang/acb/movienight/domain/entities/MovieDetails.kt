package com.ang.acb.movienight.domain.entities

data class MovieDetails(
    val movie: Movie,
    val genres: List<Genre>,
    val cast: List<Cast>,
    val trailers: List<Trailer>,
)