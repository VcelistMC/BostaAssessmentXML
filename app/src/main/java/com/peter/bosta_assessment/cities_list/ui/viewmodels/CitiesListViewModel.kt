package com.peter.bosta_assessment.cities_list.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.peter.bosta_assessment.cities_list.data.mappers.CityMapper
import com.peter.bosta_assessment.cities_list.data.models.City
import com.peter.bosta_assessment.common.network.CityService
import com.peter.bosta_assessment.common.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CitiesListViewModel @Inject constructor(
    private val cityService: CityService
): BaseViewModel() {
    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _cityList = MutableLiveData<List<City>>()
    val cityList: LiveData<List<City>>
        get() = _cityList

    fun getCities(){
        // I'd normally use a flow here to make use of the #onStart, #onError and #collect functions
        // but you specified using coroutines
        viewModelScope.launch {
            val cityMapper = CityMapper()
            _isLoading.postValue(true)
            val citiesDto = cityService.getCities("60e4482c7cb7d4bc4849c4d5")
            val mappedCities = cityMapper.mapListToModel(citiesDto.data)
            _isLoading.postValue(false)
            _cityList.postValue(mappedCities)
        }
    }
}


// TODO: error handling
// TODO: search functionality
// TODO: uncovered thing