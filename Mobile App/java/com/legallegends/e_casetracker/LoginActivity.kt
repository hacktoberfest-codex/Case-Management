package com.legallegends.e_casetracker

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.legallegends.e_casetracker.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var loginBinding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>


    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        val view = loginBinding.root
        setContentView(view)

        // Initialize Firebase Authentication
        firebaseAuth = FirebaseAuth.getInstance()

        // Initialize Google Sign-In
        initGoogleSignIn()

        // Set up background animation
        animationLoginBg()

        loginBinding.apply {
            buttonLogin.setOnClickListener {
                val email = editTextTextEmailAddress.text.toString()
                val pass = editTextTextPassword.text.toString()
                signInUser(email, pass)
            }

            buttonGoogle.setOnClickListener {
                signInGoogle()
            }

            SignUptextView.setOnClickListener {
                val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
                startActivity(intent)
            }

            forgetMyPasstextView.setOnClickListener {
                val intent = Intent(applicationContext, ForgotActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val user = firebaseAuth.currentUser
        if (user != null) {
            Toast.makeText(applicationContext, "Welcome Judges", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()  // Optionally finish the LoginActivity if needed
        }
    }

    private fun signInUser(email: String, pass: String) {
        if (email.isNotEmpty() && pass.isNotEmpty()) {
            firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this@LoginActivity, "Login successful", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()  // Optionally finish the LoginActivity if needed
                } else {
                    Toast.makeText(this@LoginActivity, "Login failed: ${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(this@LoginActivity, "Empty Fields Are Not Allowed!!😓.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveUserData() {

    }

    private fun initGoogleSignIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("734044544239-4qm92i7v8drrpnjjflaj941c49rd683c.apps.googleusercontent.com")
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
        activityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback { result ->
                val resultCode = result.resultCode
                val data = result.data

                if (resultCode == RESULT_OK && data != null) {
                    val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
                    firebaseSignInWithGoogle(task)
                }
            })
    }

    private fun signInGoogle() {
        val signInIntent: Intent = googleSignInClient.signInIntent
        activityResultLauncher.launch(signInIntent)
    }

    private fun firebaseSignInWithGoogle(task: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount = task.getResult(ApiException::class.java)
            Toast.makeText(applicationContext, "Welcome Civilians", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()  // Optionally finish the LoginActivity if needed
            firebaseGoogleAccount(account)
        } catch (e: ApiException) {
            Toast.makeText(applicationContext, "Google Sign-In failed: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun firebaseGoogleAccount(account: GoogleSignInAccount) {
        val authCredential = GoogleAuthProvider.getCredential(account.idToken, null)
        firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Handle successful Google Sign-In
                // val user = auth.currentUser
            } else {
                // Handle unsuccessful Google Sign-In
            }
        }
    }

    private fun animationLoginBg() {
        val constraintLayout: ConstraintLayout = findViewById(R.id.LoginBgLayout)
        val animationDrawable: AnimationDrawable = constraintLayout.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(5000)
        animationDrawable.setExitFadeDuration(5000)
        animationDrawable.start()
    }
}
