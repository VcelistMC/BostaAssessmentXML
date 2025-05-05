package com.peter.bosta_assessment.cities_list.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.peter.bosta_assessment.cities_list.data.models.City
import com.peter.bosta_assessment.cities_list.data.models.District
import com.peter.bosta_assessment.databinding.CityItemBinding
import com.peter.bosta_assessment.databinding.DistrictItemBinding

class DistrictsAdapter(private val districts: List<District>):  RecyclerView.Adapter<DistrictsAdapter.DistrictViewHolder>() {
    private lateinit var binding: DistrictItemBinding
    class DistrictViewHolder(private val binding: DistrictItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bindItem(district: District){
            binding.districtTextView.text = district.districtName
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DistrictViewHolder {
        binding = DistrictItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return DistrictViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DistrictsAdapter.DistrictViewHolder, position: Int) {
        holder.bindItem(districts[position])
    }

    override fun getItemCount(): Int {
        return districts.size
    }
}