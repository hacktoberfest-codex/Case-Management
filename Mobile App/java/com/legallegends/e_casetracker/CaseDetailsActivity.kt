package com.legallegends.e_casetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.legallegends.e_casetracker.databinding.ActivityCaseDetailsBinding

class CaseDetailsActivity : AppCompatActivity() {
    private lateinit var detailsBinding: ActivityCaseDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailsBinding=ActivityCaseDetailsBinding.inflate(layoutInflater)
        val view=detailsBinding.root
        setContentView(view)

        val caseNumber=intent.getStringExtra("CaseNumber")
        val caseStatus=intent.getStringExtra("CaseStatus")
        detailsBinding.apply {
            caseNumberTv.text=caseNumber
            caseStatusTv.text=caseStatus
            imageButton.setOnClickListener {
                finish()
            }
        }
    }
}