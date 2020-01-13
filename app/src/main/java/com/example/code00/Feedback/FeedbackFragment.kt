package com.example.code00.Feedback

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.code00.R
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_feedback.*


class FeedbackFragment : Fragment() {
    lateinit var i_feedback: EditText
    lateinit var i_ratingBar: RatingBar
    lateinit var save_btn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_feedback, container, false)

        i_feedback = root.findViewById(R.id.feedback)
        i_ratingBar = root.findViewById(R.id.ratingBar)
        save_btn = root.findViewById(R.id.save_btn)

        save_btn.setOnClickListener{
            saveFeedback()
        }
        return root
    }

    private fun saveFeedback(){
        val user_feedback = i_feedback.text.toString().trim()

        if(user_feedback.isEmpty()){
            i_feedback.error = "Please enter your feedback"
            return
        }

        val ref = FirebaseDatabase.getInstance().getReference("Feedbacks")
        val feedbackID = ref.push().key.toString()
        val feedback = Feedback( feedbackID, user_feedback, i_ratingBar.numStars)

        ref.child(feedbackID).setValue(feedback).addOnCompleteListener {
            Toast.makeText(context, "Feedback added successfully", Toast.LENGTH_SHORT).show()
        }

    }
}