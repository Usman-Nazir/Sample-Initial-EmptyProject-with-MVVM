package com.mvvm.example.views.homeScreen.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mvvm.example.databinding.FragmentHomeBinding
import com.mvvm.example.views.homeScreen.HomeScreen


class HomeFragment : Fragment(){

    var viewModel: HomeViewModel? = null
    var procuriotCategoriesAdapter: ProcuriotCategoriesAdapter? = null
    private var _binding: FragmentHomeBinding? = null



    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = _binding!!.root

//        val textView: TextView = binding.textHome
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inits()
    }

    private fun inits() {

        _binding?.menuBurger?.setOnClickListener { menuBurgerLogic() }

        _binding?.searchText?.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                return@OnEditorActionListener true
            }
            false
        })
        loadCurrentVenuesAccordingToLocation()
    }


    private fun loadCurrentVenuesAccordingToLocation() {

    }

    private fun menuBurgerLogic() {
        (activity as HomeScreen).openDrawer()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}