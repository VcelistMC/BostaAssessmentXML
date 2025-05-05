package com.peter.bosta_assessment.cities_list.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.peter.bosta_assessment.R
import com.peter.bosta_assessment.cities_list.data.models.City
import com.peter.bosta_assessment.databinding.CityItemBinding

class CitiesAdapter(private val cities: List<City>): RecyclerView.Adapter<CitiesAdapter.CityViewHolder>() {
    private lateinit var binding: CityItemBinding


    class CityViewHolder(private val binding: CityItemBinding): RecyclerView.ViewHolder(binding.root){
        private var _isExpanded = false

        val isExpanded: Boolean
            get() = _isExpanded

        fun bindItem(city: City){
            binding.cityTextView.text = city.cityName
            binding.districtRv.adapter = DistrictsAdapter(city.districts)
            binding.districtRv.layoutManager = LinearLayoutManager(binding.root.context)
            binding.districtRv.setHasFixedSize(true)

            bindClickEvent()
        }

        private fun toggleDistrictsVisibility(){
            _isExpanded = !isExpanded
            binding.districtRv.visibility = if(_isExpanded) View.VISIBLE else View.GONE
        }

        fun bindClickEvent(){
            binding.textImageGroup.setOnClickListener {
                toggleDistrictsVisibility()
                binding.arrowIndicator.setImageResource(
                    if(isExpanded) R.drawable.caret_up
                    else R.drawable.caret_down
                )
            }
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CityViewHolder {
        binding = CityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = cities[position]
        holder.bindItem(city)
    }

    override fun getItemCount(): Int {
        return cities.size
    }
}