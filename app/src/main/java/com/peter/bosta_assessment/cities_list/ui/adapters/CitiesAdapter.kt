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

class CitiesAdapter(private var cities: List<City>): RecyclerView.Adapter<CitiesAdapter.CityViewHolder>() {
    private lateinit var binding: CityItemBinding
    private val expandedItemsIds = HashSet<String>()

    class CityViewHolder(private val binding: CityItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bindItem(city: City, isExpanded: Boolean){
            binding.cityTextView.text = city.cityName
            binding.districtRv.adapter = DistrictsAdapter(city.districts)
            binding.districtRv.layoutManager = LinearLayoutManager(binding.root.context)
            binding.districtRv.setHasFixedSize(true)

            binding.districtRv.visibility = if(isExpanded) View.VISIBLE else View.GONE

            binding.arrowIndicator.setImageResource(
                if(isExpanded) R.drawable.caret_up
                else R.drawable.caret_down
            )
        }

        fun bindClickEvent(onClick: () -> Unit){
            binding.textImageGroup.setOnClickListener {
                onClick()
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
        val isExpanded = expandedItemsIds.contains(city.cityId)

        holder.bindItem(city, isExpanded)
        holder.bindClickEvent {
            if(isExpanded){
                expandedItemsIds.remove(city.cityId)
            }else{
                expandedItemsIds.add(city.cityId)
            }
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return cities.size
    }


    fun setCities(citiesList: List<City>){
        cities = citiesList
        notifyDataSetChanged()
    }
}