package com.legallegends.e_casetracker


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


class HomeFragment : Fragment() {
    private lateinit var recentCasesBinding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        recentCasesBinding=FragmentHomeBinding.inflate(inflater,container,false)
        return  recentCasesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageList= ArrayList<SlideModel>()
        imageList.add(SlideModel(R.drawable.flag, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.g20, ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel(R.drawable.law_books_legal_court, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.supreme_court_of_india, ScaleTypes.FIT))
        imageList.add(SlideModel(R.drawable.court, ScaleTypes.FIT))

        val imageSlider=recentCasesBinding.imageSlider
        imageSlider.setImageList(imageList)
        imageSlider.setImageList(imageList, ScaleTypes.FIT)

        imageSlider.setItemClickListener(object : ItemClickListener {
            override fun doubleClick(position: Int) {

            }

            override fun onItemSelected(position: Int) {
                val itemPosition=imageList[position]
                val itemMessage="Selected Image $position"
                Toast.makeText(requireContext(),itemMessage, Toast.LENGTH_SHORT).show()

            }

        })



        val caseNumber= listOf("101","102","103","104","105","106","107","108","109","110")
        val state= listOf("Recent","Recent","Active","Active","Pending","Pending","Recent","Recent","Pending","Pending")


//required context to get the position of fragment
        val adapter= RecentCaseAdapter(caseNumber,state,requireContext())
        recentCasesBinding.recentRecyclerView.layoutManager= LinearLayoutManager(requireContext())
        recentCasesBinding.recentRecyclerView.adapter=adapter
    }

    companion object {

    }

}