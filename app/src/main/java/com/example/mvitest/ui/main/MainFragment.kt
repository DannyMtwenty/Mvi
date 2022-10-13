package com.example.mvitest.ui.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvitest.R
import com.example.mvitest.ui.main.state.MainStateEvents
import com.example.mvitest.ui.main.state.MainStateEvents.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        //instatiating viewModel in fragment
        viewModel=activity?.run {
            ViewModelProvider(this).get(MainViewModel::class.java)
        }?:throw Exception("Invalid activity")
        subcribeObservers()
    }
    //subscribe to all observers in the viewModel
    fun subcribeObservers(){
        //when the events is fired, subscribe to all observers
        viewModel.dataState.observe(viewLifecycleOwner, Observer{dataState ->

            println("the data state is: " + dataState)
            dataState.blogLists?.let { blogs ->
              //set  blogs
                viewModel.setBlogPosts(blogs)
            }

            dataState.user?.let { user ->
                //set user
                viewModel.setUser(user)

            }
        })

        //retrieve from viewstate and set to ui
        viewModel.viewState.observe(viewLifecycleOwner,Observer{ viewState ->
           viewState.user?.let {
              println("setting user ${it}")
           }
            viewState.blogLists?.let {
                println("setting blog list to recycler ${it}")
            }

        })

    }


    //for menuInflater
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_get_blogs -> triggerBlogsEvent()
            R.id.action_get_user -> triggerUserEvent()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun triggerUserEvent() {
        viewModel.setStateEvent(GetUserEvent("1"))
    }

    private fun triggerBlogsEvent() {
        viewModel.setStateEvent(GetBlogPostsEvent())
    }

}