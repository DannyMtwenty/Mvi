package com.example.mvitest.ui.main

import androidx.constraintlayout.motion.utils.ViewState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.mvitest.ui.main.state.MainStateEvents
import com.example.mvitest.ui.main.state.MainViewState
import com.example.mvitest.utils.AbsentLiveData

class MainViewModel {
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
                return AbsentLiveData.create()
            }

            is MainStateEvents.GetUserEvent -> {
                return AbsentLiveData.create()
            }

            is MainStateEvents.None -> {
                return AbsentLiveData.create()
            }


        }
    }

}