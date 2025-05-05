package com.peter.bosta_assessment.cities_list.ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.peter.bosta_assessment.common.network.CityService
import com.peter.bosta_assessment.common.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CitiesListViewModel @Inject constructor(
    private val cityService: CityService
): BaseViewModel() {

    fun getCities(){

    }
}

// TODO: make it return a flow so we can collect it
// TODO: error handling
// TODO: search functionality
// TODO: loading
// TODO: uncovered thing