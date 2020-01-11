package com.example.code00

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast.makeText
import androidx.annotation.NonNull
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_register.*
import java.lang.Error
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.Map

class RegisterFragment : Fragment() {
    lateinit var r_username : EditText
    lateinit var r_email : EditText
    lateinit var r_password : EditText
    lateinit var r_phone : EditText
    lateinit var r_register_button : Button
    lateinit var r_sign_in : TextView
    lateinit var r_progressBar : ProgressBar
    lateinit var auth: FirebaseAuth
    lateinit var firestore: FirebaseFirestore
    lateinit var userID : String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_register, container, false)

        r_username = root.findViewById(R.id.username)
        r_email = root.findViewById(R.id.email)
        r_password = root.findViewById(R.id.password)
        r_phone = root.findViewById(R.id.phone)
        r_register_button = root.findViewById(R.id.register_button)
        r_sign_in = root.findViewById(R.id.sign_in)
        r_progressBar = root.findViewById(R.id.progressBar)

        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        if (auth.currentUser != null)
        {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        r_register_button.setOnClickListener{
                val email_insert = r_email.getText().toString().trim()
                val password_insert = r_password.getText().toString().trim()
                val username_insert = r_username.getText().toString().trim()
                val phone_insert = r_username.getText().toString().trim()

                if (TextUtils.isEmpty(email_insert)){
                    r_email.error = "Email is required!"
                }

                if (TextUtils.isEmpty(password_insert)){
                    r_password.error = "Password is required!"
                }

                if (password_insert.length < 6){
                    r_password.error = "Password length must be > 6 characters"
                }

                r_progressBar.visibility

                //register the user in firebase


                auth.createUserWithEmailAndPassword(email_insert,password_insert).addOnCompleteListener(OnCompleteListener<AuthResult>{
                @Override
                    fun onResume(@NonNull task : Task<AuthResult>){
                        if (task.isSuccessful()){
                            Toast.makeText(activity, "User Created.",Toast.LENGTH_SHORT).show()
                            userID = auth.currentUser!!.uid
                            val documentReference : DocumentReference
                            documentReference = firestore.collection("users").document(userID)

                            var user : HashMap<String, Object>
                                    = HashMap<String, Object> ()

                            /*user.put("username", f_username)
                            user.put("email",email)
                            user.put("phone",phone)*/

                            val intent = Intent(getActivity(), MainActivity::class.java)
                            startActivity(intent)
                        }
                        else
                        {
                            Toast.makeText(activity, "Error !" + task.exception.toString(),Toast.LENGTH_SHORT).show()
                            progressBar.setVisibility(View.GONE)
                        }
                    }
                })
            }

        r_sign_in.setOnClickListener {
            val intent = Intent(getActivity(), LoginFragment::class.java)
            startActivity(intent)
        }

        return root
    }
}
