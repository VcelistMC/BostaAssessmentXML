package com.peter.bosta_assessment.cities_list.data.models

data class City(
    val cityId: String,
    val cityName: String,
    val cityOtherName: String,
    val cityCode: String,
    val districts: List<District>,
    val pickupAvailability: Boolean,
    val dropOffAvailability: Boolean,
)
