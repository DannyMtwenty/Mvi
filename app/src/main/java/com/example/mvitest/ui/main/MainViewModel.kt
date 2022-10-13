package com.example.mvitest.ui.main

import androidx.constraintlayout.motion.utils.ViewState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.mvitest.models.BlogPost
import com.example.mvitest.models.User
import com.example.mvitest.ui.main.state.MainStateEvents
import com.example.mvitest.ui.main.state.MainViewState
import com.example.mvitest.utils.AbsentLiveData

class MainViewModel : ViewModel(){
    //mutable live data
    private val _viewState : MutableLiveData<MainViewState> = MutableLiveData()
    private val _stateEvent : MutableLiveData<MainStateEvents> = MutableLiveData()

    //create getter for view state
    //to be observed in main activity_main
    val viewState: LiveData<MainViewState>
        get() = _viewState

    //listen to state changes
    val dataState: LiveData<MainViewState> = Transformations.switchMap(_stateEvent){
        stateEvent ->
       stateEvent?.let {
           handleMainStateEvents(it)
       }

    }

    //handle main state events
    fun handleMainStateEvents(stateEvent: MainStateEvents) : LiveData<MainViewState>{

        when(stateEvent){

            is MainStateEvents.GetBlogPostsEvent -> {
                return object: LiveData<MainViewState>(){
                    override fun onActive() {
                        super.onActive()
                        val blogList: ArrayList<BlogPost> = ArrayList()
                        blogList.add(
                            BlogPost(
                                pk = 0,
                                title = "Vancouver PNE 2019",
                                body = "Here is Jess and I at the Vancouver PNE. We ate a lot of food.",
                                image = "https://cdn.open-api.xyz/open-api-static/static-blog-images/image8.jpg"
                            )
                        )
                        blogList.add(
                            BlogPost(
                                pk = 1,
                                title = "Ready for a Walk",
                                body = "Here I am at the park with my dogs Kiba and Maizy. Maizy is the smaller one and Kiba is the larger one.",
                                image = "https://cdn.open-api.xyz/open-api-static/static-blog-images/image2.jpg"
                            )
                        )
                        value = MainViewState(
                            blogLists = blogList
                        )
                    }
                }
            }

            is MainStateEvents.GetUserEvent -> {
                return object: LiveData<MainViewState>(){
                    override fun onActive() {
                        super.onActive()
                        val user= User(
                                email = "danny@gmail.com",
                                username = "mtwenty",
                                image = "https://cdn.open-api.xyz/open-api-static/static-blog-images/image2.jpg"
                            )

                        value = MainViewState(
                            user = user
                        )
                    }
                }
            }

            is MainStateEvents.None -> {
                return AbsentLiveData.create()
            }


        }
    }
    fun setBlogPosts(blogPosts: List<BlogPost>) {
        val update = getCurrentViewStateOrNew()
        update.blogLists = blogPosts
        _viewState.value = update

    }

    fun setUser(user: User) {
        val update = getCurrentViewStateOrNew()
        update.user = user
        _viewState.value = update


    }

    fun getCurrentViewStateOrNew() : MainViewState {
        val value = _viewState.value?.let {
            it
        } ?: MainViewState()
        return value
    }

    //trigger state event
    fun setStateEvent(eventState: MainStateEvents) {

        _stateEvent.value = eventState

    }

}