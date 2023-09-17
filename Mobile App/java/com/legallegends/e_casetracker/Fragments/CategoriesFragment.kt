package com.legallegends.e_casetracker

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.legallegends.e_casetracker.Adapters.CategoriesRecyclerViewAdapter
import com.legallegends.e_casetracker.Adapters.CategoriesRecyclerViewModel
import com.legallegends.e_casetracker.databinding.FragmentCategoriesBinding



class CategoriesFragment : Fragment() {
    private lateinit var CategoriesBinding:FragmentCategoriesBinding

    private val rvList=ArrayList<CategoriesRecyclerViewModel>()
    private lateinit var CategoriesRvAdapter:CategoriesRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        CategoriesBinding= FragmentCategoriesBinding.inflate(inflater,container,false)

         rvList.add(CategoriesRecyclerViewModel(R.drawable.civil,"Civil"))
        rvList.add(CategoriesRecyclerViewModel(R.drawable.criminal2,"Criminal"))
       rvList.add(CategoriesRecyclerViewModel(R.drawable.contract,"Contract"))
       rvList.add(CategoriesRecyclerViewModel(R.drawable.juvenile,"Juvenile"))
       rvList.add(CategoriesRecyclerViewModel(R.drawable.appeal,"Appeal"))


        CategoriesRvAdapter= CategoriesRecyclerViewAdapter()
        CategoriesBinding.CategoriesRv.adapter = CategoriesRvAdapter
        CategoriesRvAdapter.submitList(rvList)

        return  CategoriesBinding.root
    }

    companion object {}
}