package com.legallegends.e_casetracker

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.widget.ConstraintLayout

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.legallegends.e_casetracker.Model.UserModel
import com.legallegends.e_casetracker.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    lateinit var signUpBinding: ActivitySignUpBinding
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var databse:DatabaseReference

    private lateinit var email: String
    private lateinit var password:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signUpBinding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = signUpBinding.root
        setContentView(view)

        //stop from going into night mode
        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        //signUpBg()
        firebaseAuth = FirebaseAuth.getInstance()

        signUpBinding.buttonSignUp.setOnClickListener {
            val email = signUpBinding.editTextViewEmailSignUP.text.toString().trim()
            val pass = signUpBinding.editTextViewPasswordSignUP.text.toString().trim()
            val confirmPass = signUpBinding.editTextViewConfirmPasswordSignUP.text.toString().trim()
            signUpWithFireBase(email, pass, confirmPass)
        }


    }

    fun signUpWithFireBase(email: String, pass: String, confirmPass: String) {

        signUpBinding.progressCircularSignUp.visibility= View.VISIBLE
        signUpBinding.buttonSignUp.isClickable=false

        if (email.isNotEmpty() and pass.isNotEmpty() and confirmPass.isNotEmpty()) {
            if (pass == confirmPass) {
                firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(applicationContext,"Your account has been created. ✅👍😍", Toast.LENGTH_SHORT).show()
                        saveUserData()
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                        signUpBinding.progressCircularSignUp.visibility= View.INVISIBLE
                        signUpBinding.buttonSignUp.isClickable=true
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

                    }
                }
            } else {
                Toast.makeText(this, "Password is not matching 😫.", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Empty Fields Are Not Allowed!!😓.", Toast.LENGTH_SHORT).show()
            signUpBinding.progressCircularSignUp.visibility= View.INVISIBLE
            signUpBinding.buttonSignUp.isClickable=true
        }
    }

    private fun saveUserData() {
         email=signUpBinding.editTextViewEmailSignUP.toString().trim()
         password=signUpBinding.editTextViewConfirmPasswordSignUP.toString().trim()
         val user=UserModel(email,password)
        val userId: String = FirebaseAuth.getInstance().currentUser!!.uid
        databse.child("user").child(userId).setValue(user)

    }

    private fun signUpBg() {
        val constraintLayout: ConstraintLayout = findViewById(R.id.signUpLayout)
        val animationDrawable: AnimationDrawable = constraintLayout.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(1000)
        animationDrawable.setExitFadeDuration(2000)
        animationDrawable.start()
    }
}