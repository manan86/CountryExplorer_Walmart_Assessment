package com.example.walmart_assignment.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.walmart_assignment.databinding.ViewHolderCountryBinding
import com.example.walmart_assignment.domain.data.CountryDomain

/**
 * RecyclerView adapter for displaying a list of countries.
 * This adapter binds country data to the UI and efficiently updates the list using DiffUtil.
 * It ensures smooth UI performance by minimizing unnecessary updates.
 * @param countries The initial list of countries to be displayed.
 */
class CountryAdapter(private var countries: List<CountryDomain>) :
    RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    class ViewHolder(val binding: ViewHolderCountryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewHolderCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = countries[position]
        holder.binding.apply {
            tvCountry.text = "${country.name}, ${country.region}"
            tvCode.text = country.code
            tvCapital.text = country.capital
        }

    }

    override fun getItemCount() = countries.size

    fun updateData(newCountries: List<CountryDomain>) {
        val diffCallback = CountryDiffCallback(countries, newCountries)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        countries = newCountries
        diffResult.dispatchUpdatesTo(this)
    }
}

class CountryDiffCallback(
    private val oldList: List<CountryDomain>,
    private val newList: List<CountryDomain>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].code == newList[newItemPosition].code
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}

