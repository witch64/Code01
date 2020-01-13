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
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_register.*
import java.lang.Error
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.Map

class RegisterFragment : Fragment() {
    lateinit var r_username : EditText
    lateinit var r_password : EditText
    lateinit var r_retype_password : EditText
    lateinit var r_register_button : Button
    lateinit var r_sign_in : TextView
    lateinit var r_progressBar : ProgressBar
    lateinit var db : DatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_register, container, false)

        val db = DatabaseHelper(context)
        r_username = root.findViewById(R.id.username)
        r_password = root.findViewById(R.id.password)
        r_retype_password = root.findViewById(R.id.retype_password)
        r_register_button = root.findViewById(R.id.register_button)
        r_sign_in = root.findViewById(R.id.sign_in)
        r_progressBar = root.findViewById(R.id.progressBar)

        val fragment: Fragment

        val transaction: FragmentTransaction =
            fragmentManager!!.beginTransaction()

        r_sign_in.setOnClickListener {
            transaction.replace(R.id.fragment_container,
                LoginFragment())
            transaction.addToBackStack(null) // this will manage backstack
            transaction.commit()
        }

        r_register_button.setOnClickListener{
            val user : String = r_username.text.toString().trim()
            val pwd : String = r_password.text.toString().trim()
            val retype_pwd : String = r_retype_password.text.toString().trim()

            if (!TextUtils.isEmpty(user)){
                if (!TextUtils.isEmpty(retype_pwd)){
                    if (!TextUtils.isEmpty(pwd)){
                    }
                    if (pwd.equals(retype_pwd)){
                        val check : Long = db.addUser(user,pwd)
                        if (check > 0){
                            progressBar.visibility = View.VISIBLE
                            Toast.makeText(context,"Register Successful", Toast.LENGTH_LONG).show()
                            transaction.replace(R.id.fragment_container,
                                LoginFragment())
                            transaction.addToBackStack(null) // this will manage backstack
                            transaction.commit()
                        }else{
                            Toast.makeText(context,"User exist", Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Toast.makeText(context,"Password is not match", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else
            {
                Toast.makeText(context,"Please fill in the blank", Toast.LENGTH_SHORT).show()
            }
        }
        return root
    }
}