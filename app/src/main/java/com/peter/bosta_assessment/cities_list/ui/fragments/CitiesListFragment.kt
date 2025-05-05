package com.peter.bosta_assessment.cities_list.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.peter.bosta_assessment.R
import com.peter.bosta_assessment.cities_list.data.models.City
import com.peter.bosta_assessment.cities_list.ui.adapters.CitiesAdapter
import com.peter.bosta_assessment.databinding.FragmentCitiesListBinding

class CitiesListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var cityAdapter: CitiesAdapter
    lateinit var binding: FragmentCitiesListBinding


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
        cityAdapter = CitiesAdapter(City.mockList)
        binding.cities.layoutManager = LinearLayoutManager(requireContext())
        binding.cities.adapter = cityAdapter
    }


    companion object {
        @JvmStatic
        fun newInstance() = CitiesListFragment()
    }
}