package com.peter.bosta_assessment.cities_list.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.peter.bosta_assessment.R
import com.peter.bosta_assessment.cities_list.data.models.City
import com.peter.bosta_assessment.cities_list.ui.adapters.CitiesAdapter
import com.peter.bosta_assessment.cities_list.ui.viewmodels.CitiesListViewModel
import com.peter.bosta_assessment.common.ui.BaseFragment
import com.peter.bosta_assessment.databinding.FragmentCitiesListBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CitiesListFragment @Inject constructor() : BaseFragment<CitiesListViewModel>() {
    private lateinit var cityAdapter: CitiesAdapter
    private lateinit var binding: FragmentCitiesListBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCitiesListBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        initObservers()
        initListeners()
        viewModel.getCities()
    }

    private fun initListeners() {
        binding.searchField.doOnTextChanged { text, _, _, _ ->
            viewModel.searchCities(text.toString())
        }
    }

    private fun initObservers(){
        viewModel.isLoading.observe(viewLifecycleOwner){ isLoading ->
            binding.loading.visibility = if(isLoading) View.VISIBLE else View.GONE
        }

        viewModel.cityList.observe(viewLifecycleOwner){ list ->
            cityAdapter.setCities(list)
        }
    }

    private fun initRecycler(){
        cityAdapter = CitiesAdapter(emptyList())
        binding.cities.layoutManager = LinearLayoutManager(requireContext())
        binding.cities.adapter = cityAdapter
    }


    companion object {
        @JvmStatic
        fun newInstance() = CitiesListFragment()
    }
}