package com.example.code00.Feedback

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.code00.R
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_feedback.*


class FeedbackFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        //feedbackViewModel =
        //    ViewModelProviders.of(this).get(FeedbackViewModel::class.java)


        val root = inflater.inflate(R.layout.fragment_feedback, container, false)


        return root
    }



}