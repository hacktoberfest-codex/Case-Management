package com.legallegends.e_casetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.legallegends.e_casetracker.Fragments.CustomFragment
import com.legallegends.e_casetracker.Model.CustomItems
import com.legallegends.e_casetracker.databinding.ActivityCaseDetailsBinding

class CaseDetailsActivity : AppCompatActivity() {
    private lateinit var detailsBinding: ActivityCaseDetailsBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private  var caseNumber: String ?=null
    private  var caseStatus: String ?=null
    private  var city: String ?=null
    private  var categories: String ?=null
    private  var type: String ?=null
    private  var lawyerName: String ?=null
    private  var caseDetails: String ?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailsBinding=ActivityCaseDetailsBinding.inflate(layoutInflater)
        val view=detailsBinding.root
        setContentView(view)

        //initialize fireBase auth
        firebaseAuth=FirebaseAuth.getInstance()

         caseNumber=intent.getStringExtra("CaseNumber")
         caseStatus=intent.getStringExtra("CaseStatus")
        val CaseSpanValue = intent.getDoubleExtra("SpanValue", 0.0)
        val CaseType = intent.getStringExtra("Type")


        detailsBinding.apply {
            caseNumberTv.text=caseNumber
            caseStatusTv.text=caseStatus
            typeTv.text=CaseSpanValue.toString()
            city=cityTv.text.toString()
            categories=catTv.text.toString()
            typeTv.text=CaseType.toString()
            type=typeTv.text.toString()
            spanTv.text=CaseSpanValue.toString()
            lawyerName=lawyerTv.text.toString()
            caseDetails=detTv.text.toString()

            imageButton.setOnClickListener {
                finish()
            }

            PlusBtn.setOnClickListener {
                addItemToCustom()

                    // Pass the data to CustomFragment
                    val customItem = CustomItems(
                        caseNumber.toString(),
                        caseStatus.toString(),
                        cityTv.text.toString(),
                        catTv.text.toString(),
                        typeTv.text.toString(),
                        lawyerTv.text.toString(),
                        detTv.text.toString()
                    )

                    val customFragment = CustomFragment()
                    customFragment.addItemToRecyclerView(customItem)

            }
        }
    }

    private fun addItemToCustom() {
         val database=FirebaseDatabase.getInstance().reference
        val userId=firebaseAuth.currentUser?.uid?:""
        val customItem =CustomItems(caseNumber.toString(),caseStatus.toString(),city.toString(),categories.toString(),type.toString(),lawyerName.toString(),caseDetails.toString())
        // save in fire base
        database.child("user").child(userId).child("CustomItem").push().setValue(customItem).addOnSuccessListener {
            Toast.makeText(this,"This item add into custom successfully😍",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this,"This item is not added to custom 😫",Toast.LENGTH_SHORT).show()
        }
    }
}
