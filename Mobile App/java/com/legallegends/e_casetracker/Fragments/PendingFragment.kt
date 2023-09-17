package com.legallegends.e_casetracker

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.legallegends.e_casetracker.Adapters.RecentCaseAdapter
import com.legallegends.e_casetracker.databinding.FragmentPendingBinding


class PendingFragment : Fragment(), RecentCaseAdapter.OnItemClickListener {

lateinit var pendingBinding: FragmentPendingBinding
  private val caseNumber= listOf("201","202","203","204","205","206","207","208","209","210")
   private val state= listOf("Pending","Pending","Pending","Pending","Pending","Pending","Pending","Pending","Pending","Pending")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        pendingBinding=FragmentPendingBinding.inflate(inflater,container,false)
        return  pendingBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val adapter= RecentCaseAdapter(caseNumber,state,requireContext(),this)
        pendingBinding.PendingRecyclerView.layoutManager= LinearLayoutManager(requireContext())
        pendingBinding.PendingRecyclerView.adapter=adapter

    }

    override fun onItemClick(position: Int) {
        val selectedCaseNumber = caseNumber[position]
        val selectedCaseStatus = state[position]

        val intent = Intent(requireContext(), CaseDetailsActivity::class.java)
        intent.putExtra("CaseNumber", selectedCaseNumber)
        intent.putExtra("CaseStatus", selectedCaseStatus)
        startActivity(intent)
    }
}