package com.example.code00

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class LoginSelectFragment : Fragment() {
    lateinit var login_button : Button
    lateinit var register_button : Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_homepage, container, false)

        login_button = root.findViewById(R.id.login_button)
        register_button = root.findViewById(R.id.register_button)

        val fragment: Fragment

        val transaction: FragmentTransaction =
            fragmentManager!!.beginTransaction()

        login_button.setOnClickListener{
            transaction.replace(R.id.fragment_container,
                LoginFragment())
            transaction.addToBackStack(null) // this will manage backstack
            transaction.commit()
        }

        register_button.setOnClickListener{
            transaction.replace(R.id.fragment_container,
                RegisterFragment())
            transaction.addToBackStack(null) // this will manage backstack
            transaction.commit()
        }

        return root
    }
}