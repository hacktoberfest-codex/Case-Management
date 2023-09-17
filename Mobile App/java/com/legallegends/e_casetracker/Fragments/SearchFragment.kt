package com.legallegends.e_casetracker

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.legallegends.e_casetracker.Adapters.RecentCaseAdapter
import com.legallegends.e_casetracker.databinding.FragmentSearchBinding

class SearchFragment : Fragment(), RecentCaseAdapter.OnItemClickListener {

    private lateinit var searchBinding: FragmentSearchBinding
    private lateinit var adapter: RecentCaseAdapter
    private val originalCaseNumbers = listOf("101", "102", "103", "104", "105", "106", "107", "108", "109", "110","201", "202", "203", "204", "205", "206", "207", "208", "209", "210")
    private val originalCaseStates = listOf("Recent", "Recent", "Active", "Active", "Pending", "Pending", "Recent", "Recent", "Pending", "Pending","Pending","Pending","Pending","Pending","Pending","Pending","Pending","Pending","Pending","Pending")

    private val filteredCaseNumbers = mutableListOf<String>()
    private val filteredCaseStates = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchBinding = FragmentSearchBinding.inflate(inflater, container, false)

        // Initialize filtered lists with original data
        filteredCaseNumbers.addAll(originalCaseNumbers)
        filteredCaseStates.addAll(originalCaseStates)

        adapter = RecentCaseAdapter(filteredCaseNumbers, filteredCaseStates, requireContext(),this)
        searchBinding.menuRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        searchBinding.menuRecyclerView.adapter = adapter

        setupSearchView()

        showAllCases() // Updated function name

        return searchBinding.root
    }

    private fun showAllCases() { // Updated function name
        filteredCaseNumbers.clear()
        filteredCaseStates.clear()

        filteredCaseNumbers.addAll(originalCaseNumbers)
        filteredCaseStates.addAll(originalCaseStates)

        adapter.notifyDataSetChanged()
    }

    private fun setupSearchView() {
        searchBinding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                filterCaseItems(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterCaseItems(newText)
                return true
            }
        })
    }

    private fun filterCaseItems(query: String) {
        filteredCaseNumbers.clear()
        filteredCaseStates.clear()

        originalCaseNumbers.forEachIndexed { index, caseNumber ->
            if (caseNumber.contains(query, ignoreCase = true)) {
                filteredCaseNumbers.add(caseNumber)
                filteredCaseStates.add(originalCaseStates[index])
            }
        }
        adapter.notifyDataSetChanged()
    }

    override fun onItemClick(position: Int) {
        val selectedCaseNumber = originalCaseNumbers[position]
        val selectedCaseStatus = originalCaseStates[position]

        val intent = Intent(requireContext(), CaseDetailsActivity::class.java)
        intent.putExtra("CaseNumber", selectedCaseNumber)
        intent.putExtra("CaseStatus", selectedCaseStatus)
        startActivity(intent)
    }
}
