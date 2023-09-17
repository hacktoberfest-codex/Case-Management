package com.legallegends.e_casetracker

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.legallegends.e_casetracker.Adapters.RecentCaseAdapter
import com.legallegends.e_casetracker.databinding.ActivityCaseCategorieBinding

class CaseCategorieActivity : AppCompatActivity(), RecentCaseAdapter.OnItemClickListener {

    private lateinit var caseCategorieBinding: ActivityCaseCategorieBinding
    private val caseNumbers = listOf("101", "102", "103", "104", "105", "106", "107", "108", "109", "110")
    private val caseStates = listOf("Recent", "Recent", "Active", "Active", "Pending", "Pending", "Recent", "Recent", "Pending", "Pending")

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
        }

        if (caseNumberList.isNullOrEmpty() || stateList.isNullOrEmpty()) {
            Toast.makeText(this, "Case data not found", Toast.LENGTH_SHORT).show()
        } else {
            val adapter = RecentCaseAdapter(caseNumbers, caseStates, this, this)
            caseCategorieBinding.caseTypeRv.layoutManager = LinearLayoutManager(this)
            caseCategorieBinding.caseTypeRv.adapter = adapter
        }
    }

    override fun onItemClick(position: Int) {
        val selectedCaseNumber = caseNumbers[position]
        val selectedCaseStatus = caseStates[position]

        val intent = Intent(this, CaseDetailsActivity::class.java)
        intent.putExtra("CaseNumber", selectedCaseNumber)
        intent.putExtra("CaseStatus", selectedCaseStatus)
        startActivity(intent)
    }
}
