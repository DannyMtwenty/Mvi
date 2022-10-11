package com.example.mvitest.ui.main.state

sealed class MainStateEvents{
    class GetBlogPostsEvent : MainStateEvents()
    class GetUserEvent(val user_id : String): MainStateEvents()
    class None: MainStateEvents()
}
