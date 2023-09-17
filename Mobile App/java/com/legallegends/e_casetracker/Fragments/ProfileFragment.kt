package com.legallegends.e_casetracker

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.legallegends.e_casetracker.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    lateinit var profileBinding: FragmentProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        profileBinding = FragmentProfileBinding.inflate(inflater,container,false)
        profileBinding.ButtonSignOut.setOnClickListener {
            signOut()
        }
        return profileBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    private fun signOut() {
        // email and password signOut
        FirebaseAuth.getInstance().signOut()

        // google account sign out
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail().build()

        val googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
        googleSignInClient.signOut().addOnCompleteListener { task ->

            if (task.isSuccessful) {
                Toast.makeText(requireContext(), "Sign Out is successful✅💕👍", Toast.LENGTH_SHORT).show()
                val intent = Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }

        }
    }

    companion object {

    }
}
