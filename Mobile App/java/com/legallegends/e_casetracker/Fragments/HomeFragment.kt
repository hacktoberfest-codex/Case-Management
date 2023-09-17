package com.legallegends.e_casetracker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.legallegends.e_casetracker.Adapters.RecentCaseAdapter
import com.legallegends.e_casetracker.databinding.FragmentHomeBinding


class HomeFragment : Fragment(), RecentCaseAdapter.OnItemClickListener {

    private lateinit var recentCasesBinding: FragmentHomeBinding
    private lateinit var caseNumber: List<String>
    private lateinit var state: List<String>
    private lateinit var span: List<Pair<Double,String>>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        recentCasesBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return recentCasesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageList = ArrayList<SlideModel>()
        imageList.add(SlideModel(R.drawable.flag, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.g20, ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(R.drawable.law_books_legal_court, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.supreme_court_of_india, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.court, ScaleTypes.FIT))

        val imageSlider = recentCasesBinding.imageSlider
        imageSlider.setImageList(imageList)
        imageSlider.setImageList(imageList, ScaleTypes.FIT)

        imageSlider.setItemClickListener(object : ItemClickListener {
            override fun doubleClick(position: Int) {

            }

            override fun onItemSelected(position: Int) {
                val itemPosition = imageList[position]
                val itemMessage = "Selected Image $position"
                Toast.makeText(requireContext(), itemMessage, Toast.LENGTH_SHORT).show()
            }
        })

         caseNumber = listOf("101", "102", "103", "104", "105", "106", "107", "108", "109", "110")
        state = listOf("Recent", "Recent", "Active", "Active", "Pending", "Pending", "Recent", "Recent", "Pending", "Pending")
        span = listOf(0.51 to "Breach of contract ",0.66 to "product liability" ,2.0 to "Class Action",0.5 to "Personal injury",2.5 to "Murder",0.51 to "Breach of contract ",0.66 to "product liability" ,2.0 to "Class Action",0.5 to "Personal injury",2.5 to "Murder")


        // Combine caseNumber, state, and span into a list of triples
        val combinedList = caseNumber.zip(state).zip(span) { (caseNum, state), span ->
            Triple(caseNum, state, span)
        }

        // Sort the list based on the first value of span
        val sortedList = combinedList.sortedBy { it.third.first }

        // Extract the sorted caseNumber, state, and span from the sorted list
         caseNumber = sortedList.map { it.first }
          state = sortedList.map { it.second }
          span = sortedList.map { it.third }



        val adapter = RecentCaseAdapter(caseNumber, state, requireContext(),this)
        recentCasesBinding.recentRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        recentCasesBinding.recentRecyclerView.adapter = adapter
    }

    override fun onItemClick(position: Int) {
        // Handle item click here
        val selectedCaseNumber = caseNumber[position]
        val selectedCaseStatus = state[position]
        val selectedSpan=span[position]

        val intent = Intent(requireContext(), CaseDetailsActivity::class.java)
        intent.putExtra("CaseNumber", selectedCaseNumber)
        intent.putExtra("CaseStatus", selectedCaseStatus)
        intent.putExtra("SpanValue", selectedSpan.first)
        intent.putExtra("Type", selectedSpan.second)
        startActivity(intent)
    }
}
