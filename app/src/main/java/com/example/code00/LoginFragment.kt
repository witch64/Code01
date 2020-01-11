package com.example.code00

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {
    lateinit var uEmail : EditText
    lateinit var uPassword : EditText
    lateinit var ulogin_button : Button
    lateinit var uNew_user : TextView
    lateinit var progressBar: ProgressBar
    lateinit var auth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_register, container, false)

        uEmail = root.findViewById(R.id.email)
        uPassword = root.findViewById(R.id.password)
        ulogin_button = root.findViewById(R.id.login_button)
        uNew_user = root.findViewById(R.id.new_user)
        progressBar = root.findViewById(R.id.progressBar)
        auth = FirebaseAuth.getInstance()

        ulogin_button.setOnClickListener(View.OnClickListener() {
            fun onClick(view: View){
                val email : String = uEmail.text.toString().trim()
                val password : String = uPassword.text.toString().trim()

                if (TextUtils.isEmpty(email)){
                    uEmail.setError("Email is required")
                    return

                }

                if (TextUtils.isEmpty(password)){
                    uPassword.setError("Password is Required!")
                    return
                }

                if (password.length < 6){
                    uPassword.setError("Password must be > 6 characters")
                    return
                }

                progressBar.setVisibility(View.VISIBLE)

                //authenticate the user

                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(
                    OnCompleteListener<AuthResult> {
                        fun onComplete(@NonNull task: Task<AuthResult>){
                            if (task.isSuccessful){
                                Toast.makeText(activity,"Login Successful", Toast.LENGTH_SHORT).show()
                                val intent = Intent(getActivity(), MainActivity::class.java)
                                startActivity(intent)
                            }
                            else
                            {
                                Toast.makeText(activity, "Error ! " + task.exception.toString() , Toast.LENGTH_SHORT).show()
                                progressBar.setVisibility(View.GONE)
                            }
                        }
                    })
            }
        })

        uNew_user.setOnClickListener {
            val intent = Intent(getActivity(), RegisterFragment::class.java)
            startActivity(intent)
        }

        return root
    }
}