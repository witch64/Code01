package com.example.code00

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.code00.Feedback.FeedbackFragment

class HomeFragment : Fragment() {
    lateinit var h_play_btn : Button
    lateinit var h_leaderboard_btn : Button
    lateinit var h_feedback_btn : Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        val root =  inflater.inflate(R.layout.fragment_home, container, false)

        h_play_btn = root.findViewById(R.id.play_button)
        h_leaderboard_btn = root.findViewById(R.id.leaderboard_button)
        h_feedback_btn = root.findViewById(R.id.feedback_button)

        val fragment: Fragment

        val transaction: FragmentTransaction =
            fragmentManager!!.beginTransaction()

        h_play_btn.setOnClickListener{
            transaction.replace(R.id.fragment_container,
                PlayFragment())
            transaction.addToBackStack(null) // this will manage backstack
            transaction.commit()
        }

        h_leaderboard_btn.setOnClickListener{
            transaction.replace(R.id.fragment_container,
                LeaderBoardFragment())
            transaction.addToBackStack(null) // this will manage backstack
            transaction.commit()
        }

        h_feedback_btn.setOnClickListener{
            transaction.replace(R.id.fragment_container,
                FeedbackFragment())
            transaction.addToBackStack(null) // this will manage backstack
            transaction.commit()
        }


        return root
    }
}