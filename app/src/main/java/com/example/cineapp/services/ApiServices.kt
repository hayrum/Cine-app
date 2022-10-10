package com.example.cineapp.services

import com.example.cineapp.services.objects.account.Account
import com.example.cineapp.services.objects.authentication.AuthenticationToken
import com.example.cineapp.services.objects.movies.DetailMovie
import com.example.cineapp.services.objects.movies.Movies
import com.example.cineapp.services.objects.movies.MoviesPreview
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * @author Hayrum Vega
 * @since 09/10/2022
 * @description This interface class is for create the endPoints for restAPI.
 */
interface ApiServices {

    /**
     * Method GET for generate the new access token.
     */
    @GET("/authentication/token/new")
    fun getNewToken(): Call<AuthenticationToken>

    /**
     * Get information of the account.
     */
    @GET("account/{api_token}")
    fun getInfoAccount(
        @Path("api_token") api_token: String
    ): Call<Account>

    /**
     * Get list of the movies linked by the movie selected.
     */
    @GET("movie/{movie_id}/lists/{page}")
    fun getListOfMovies(
        @Path("movie_id") movie_id: Int,
        @Path("page") page: Int
    ): Call<MoviesPreview>

    /**
     * Get list of the recommendations movies
     */
    @GET("movie/{movie_id}/recommendations/{page}")
    fun getListOfRecommendationsMovies(
        @Path("movie_id") movie_id: Int,
        @Path("page") page: Int
    ): Call<Movies>

    /**
     * Get list of the popular movies.
     */
    @GET("movie/popular/{page}")
    fun getListOfPopularMovies(
        @Path("movie_id") movie_id: Int,
        @Path("page") page: Int
    ): Call<Movies>

    /**
     * Get detail of the movie selected by id.
     */
    @GET("movie/{movie_id}")
    fun getDetailOfMovie(
        @Path("movie_id") idMovie: Int
    ): Call<DetailMovie>

}