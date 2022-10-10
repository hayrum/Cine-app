package com.example.cineapp.services.objects.movies

import com.google.gson.annotations.SerializedName


data class MoviesPreview(
    @SerializedName("id")
    val idMovie: Long,
    @SerializedName("page")
    val currentPage: Long,
    @SerializedName("results")
    val listOfMovies: List<MovieDetail>,
    @SerializedName("total_pages")
    val totalPages: Long,
    @SerializedName("total_results")
    val totalResults: Long
)

data class MovieDetail(
    @SerializedName("description")
    val description: String,
    @SerializedName("favorite_count")
    val countFavorite: Long,
    @SerializedName("id")
    val idMovie: Long,
    @SerializedName("item_count")
    val countItem: Long,
    @SerializedName("iso_639_1")
    val language: String,
    @SerializedName("list_type")
    val typeOfMovie: String,
    @SerializedName("name")
    val nameMovie: String,
    @SerializedName("poster_path")
    val posterUrl: String?

)
