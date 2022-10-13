package com.example.mvitest.ui.main.state

import com.example.mvitest.models.BlogPost
import com.example.mvitest.models.User

//wrapper for the blog post and user
data class MainViewState(
    var blogLists: List<BlogPost> ? = null,
    var user : User? = null,
)
