package com.example.walmart_assignment.presentation.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.walmart_assignment.data.remote.DependencyProvider
import com.example.walmart_assignment.databinding.ActivityMainBinding
import com.example.walmart_assignment.presentation.viewmodel.CountryViewModel
import kotlinx.coroutines.launch

/**
 * Main activity displaying the list of countries.
 * This activity initializes the UI components, sets up RecyclerView for displaying country data,
 * observes data changes from [CountryViewModel], and implements a search bar for filtering results.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CountryViewModel
    private lateinit var adapter: CountryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = CountryViewModel(DependencyProvider.getCountriesUseCase)

        setupRecyclerView()
        observeData()
        setupSearchBar()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CountryAdapter(emptyList())
        binding.recyclerView.adapter = adapter
    }


    private fun observeData() {
        lifecycleScope.launch {
            viewModel.filteredCountries.collect { countryList ->
                adapter.updateData(countryList)
            }
        }
    }

    private fun setupSearchBar() {
        binding.searchBar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.setSearchQuery(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
}
