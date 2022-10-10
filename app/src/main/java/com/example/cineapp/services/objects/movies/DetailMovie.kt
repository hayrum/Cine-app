package com.example.cineapp.services.objects.movies

import com.google.gson.annotations.SerializedName

data class DetailMovie(
    @SerializedName("adult")
    val isAdultMovie: Boolean,
    @SerializedName("backdrop_path")
    val backdropUrl: String,
    @SerializedName("genres")
    val genresOfMovie: List<Genres>,
    @SerializedName("original_title")
    val originalTitleMovie: String,
    @SerializedName("overview")
    val overviewMovie: String,
    @SerializedName("popularity")
    val popularityMovie: Double,
    @SerializedName("poster_path")
    val posterUrl: String,
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompanies>,
    @SerializedName("release_date")
    val releaseDateMovie: String,
    @SerializedName("runtime")
    val runTimeMovie: Long,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val countVotes: Long
)

data class Genres(
    @SerializedName("id")
    val idGenre: Long,
    @SerializedName("name")
    val genreName: String
)

data class ProductionCompanies(
    @SerializedName("id")
    val idCompanie: Long,
    @SerializedName("logo_path")
    val logoUrl: String,
    @SerializedName("name")
    val nameCompanie: String,
    @SerializedName("origin_country")
    val originCountry: String
)
