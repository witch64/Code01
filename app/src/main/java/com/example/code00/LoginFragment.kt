package com.example.code00

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class LoginFragment : Fragment() {
    lateinit var uUsername : EditText
    lateinit var uPassword : EditText
    lateinit var ulogin_button : Button
    lateinit var uNew_user : TextView
    lateinit var progressBar: ProgressBar
    lateinit var db : DatabaseHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_login, container, false)

        val db = DatabaseHelper(context)
        uUsername = root.findViewById(R.id.username)
        uPassword = root.findViewById(R.id.password)
        ulogin_button = root.findViewById(R.id.login_button)
        uNew_user = root.findViewById(R.id.new_user)
        progressBar = root.findViewById(R.id.progressBar2)

        val fragment: Fragment

        val transaction: FragmentTransaction =
            fragmentManager!!.beginTransaction()

        uNew_user.setOnClickListener{
            transaction.replace(R.id.fragment_container,
                RegisterFragment())
            transaction.addToBackStack(null) // this will manage backstack
            transaction.commit()
        }

        ulogin_button.setOnClickListener{
            val user : String = uUsername.text.toString().trim()
            val pwd : String = uPassword.text.toString().trim()
            val res : Boolean = db.checkUser(user,pwd)

            if (!TextUtils.isEmpty(user)){
                if (!TextUtils.isEmpty(pwd)){
                }
                if (res == true){
                    progressBar.visibility = View.VISIBLE
                    Toast.makeText(context,"Login Successful", Toast.LENGTH_LONG).show()
                    transaction.replace(R.id.fragment_container,
                        HomeFragment())
                    transaction.addToBackStack(null) // this will manage backstack
                    transaction.commit()
                }else{
                    Toast.makeText(context,"Login Error", Toast.LENGTH_SHORT).show()
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