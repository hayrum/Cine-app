package com.example.cineapp.services.objects.movies

import com.google.gson.annotations.SerializedName


data class Movies(
    @SerializedName("page")
    val currentPage: Long,
    @SerializedName("results")
    val moviesList: List<MovieDetailRecommendation>
)

data class MovieDetailRecommendation(
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backDropUrl: String,
    @SerializedName("id")
    val idMovie: Long,
    @SerializedName("title")
    val titleMovie: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("overview")
    val overviewMovie: String,
    @SerializedName("poster_path")
    val posterUrl: String,
    @SerializedName("media_type")
    val typeOfMovie: String,
    @SerializedName("genre_ids")
    val listOfGenreIds: List<Int>,
    @SerializedName("popularity")
    val popularityMovie: String,
    @SerializedName("release_date")
    val releaseDateMovie: String,
    @SerializedName("video")
    val withVideo: Boolean,
    @SerializedName("vote_average")
    val averageMovie: Double,
    @SerializedName("vote_count")
    val accountVoteMovie: Long
)
