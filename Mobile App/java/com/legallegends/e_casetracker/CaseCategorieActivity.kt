package com.legallegends.e_casetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.legallegends.e_casetracker.Adapters.RecentCaseAdapter
import com.legallegends.e_casetracker.databinding.ActivityCaseCategorieBinding

class CaseCategorieActivity : AppCompatActivity() {

    private lateinit var caseCategorieBinding: ActivityCaseCategorieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        caseCategorieBinding = ActivityCaseCategorieBinding.inflate(layoutInflater)
        val view = caseCategorieBinding.root
        setContentView(view)

        val itemTitle = intent.getStringExtra("ITEM_TITLE")
        val caseNumberList = intent.getStringArrayListExtra("CaseNumber")
        val stateList = intent.getStringArrayListExtra("state")

        if (itemTitle != null) {
            caseCategorieBinding.textViewCaseType.text = itemTitle
        } else {
            Toast.makeText(this, "Item title not found", Toast.LENGTH_SHORT).show()
            // Handle the absence of item title
        }

        // Check if the lists are null or empty
        if (caseNumberList.isNullOrEmpty() || stateList.isNullOrEmpty()) {
            Toast.makeText(this, "Case data not found", Toast.LENGTH_SHORT).show()
            // Handle the absence of case data
        } else {
            // Use the retrieved values to set up the adapter
            val adapter = RecentCaseAdapter(caseNumberList, stateList, this@CaseCategorieActivity)
            caseCategorieBinding.caseTypeRv.layoutManager= LinearLayoutManager(this@CaseCategorieActivity)
            caseCategorieBinding.caseTypeRv.adapter = adapter
        }
    }
}
