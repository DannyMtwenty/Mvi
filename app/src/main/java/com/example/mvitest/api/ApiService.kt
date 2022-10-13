package com.example.mvitest.api

import com.example.mvitest.models.BlogPost
import com.example.mvitest.models.User
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("placeholder/user/{user_id}")
    fun getUser(
        @Path("user_id") userId: String
    ) : User

    @GET("placeholder/blogs")
    fun getBlogPosts() : List<BlogPost>
}