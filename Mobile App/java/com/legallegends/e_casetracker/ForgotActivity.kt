package com.legallegends.e_casetracker
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

import com.google.firebase.auth.FirebaseAuth
import com.legallegends.e_casetracker.databinding.ActivityForgotBinding

class ForgotActivity : AppCompatActivity() {

    lateinit var forgotBinding: ActivityForgotBinding
    val firebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        forgotBinding = ActivityForgotBinding.inflate(layoutInflater)
        val view = forgotBinding.root
        setContentView(view)
        background()

        forgotBinding.buttonResetPassword.setOnClickListener {
            val userEmail = forgotBinding.editTextViewEmailResetPassword.text.toString()
            if (userEmail.isNotEmpty()) {
                firebaseAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            applicationContext,
                            "We sent a password reset mail to your email address ✅✅😌",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                    } else {
                        Toast.makeText(
                            applicationContext,
                            task.exception?.localizedMessage,
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }
            }
            else{
                Toast.makeText(applicationContext,"Empty fields are not allowed 😓.",Toast.LENGTH_SHORT).show()
            }

        }
    }



    private fun background(){
        val constraintLayout: ConstraintLayout = findViewById(R.id.ForgotLayout)
        val animationDrawable: AnimationDrawable = constraintLayout.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(1000)
        animationDrawable.setExitFadeDuration(2000)
        animationDrawable.start()
    }
}