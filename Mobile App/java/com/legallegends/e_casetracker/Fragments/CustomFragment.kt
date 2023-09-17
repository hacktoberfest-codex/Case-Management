// CustomFragment.kt
package com.legallegends.e_casetracker.Fragments

import CustomFragmentAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.legallegends.e_casetracker.Model.CustomItems
import com.legallegends.e_casetracker.databinding.FragmentCustomBinding

class CustomFragment : Fragment() {
    private lateinit var customBinding: FragmentCustomBinding
    private lateinit var detailsAdapter: CustomFragmentAdapter
    private val customItemsList = mutableListOf<CustomItems>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        customBinding = FragmentCustomBinding.inflate(inflater, container, false)
        return customBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailsAdapter = CustomFragmentAdapter(customItemsList)
        customBinding.customRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        customBinding.customRecyclerView.adapter = detailsAdapter
    }

    fun addItemToRecyclerView(customItem: CustomItems) {
        customItemsList.add(customItem)
        detailsAdapter.notifyDataSetChanged()
    }
}
