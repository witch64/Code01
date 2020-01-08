package com.example.code00

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_feedback.*
import com.example.code00.R


class FeedbackFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        lateinit var ref: DatabaseReference
        lateinit var save_button: Button
        val root = inflater.inflate(R.layout.fragment_feedback, container, false)

        ref = FirebaseDatabase.getInstance().getReference("feedback")
        save_button = root.findViewById(R.id.save_btn)


        save_button.setOnClickListener{
            saveFeedback()
        }
        return root
    }


    private fun saveFeedback(){
        val user_feedback = feedback.toString().trim()

        if(user_feedback.isEmpty()){
            feedback.error = "Please enter your feedback"
            return
        }

        val ref = FirebaseDatabase.getInstance().getReference("feedbacks")
        val feedback_id = ref.push().key.toString()

        val feedback = Feedback(feedback_id, user_feedback, ratingBar.numStars)

        ref.child(feedback_id).setValue(feedback).addOnCompleteListener{
            Toast.makeText(getActivity(),"Feedback added successfully", Toast.LENGTH_SHORT).show()
        }
    }
}