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
    lateinit var ref : DatabaseReference
    lateinit var feedbackList : MutableList<Feedback>
    lateinit var v_feedback_list : ListView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_feedback, container, false)

        feedbackList = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("Feedbacks")
        i_feedback = root.findViewById(R.id.feedback)
        i_ratingBar = root.findViewById(R.id.ratingBar)
        save_btn = root.findViewById(R.id.save_btn)
        v_feedback_list = root.findViewById(R.id.feedback_list)

        save_btn.setOnClickListener{
            saveFeedback()
        }
        
        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()){
                    feedbackList.clear()
                    for (f in p0.children){
                        val feedback = f.getValue(Feedback::class.java)
                        feedbackList.add(feedback!!)
                    }

                    val adapter = FeedbackAdapter(context!!, R.layout.feedbacks, feedbackList)
                    v_feedback_list.adapter = adapter
                }
            }

        })
        return root
    }

    private fun saveFeedback(){
        val user_feedback = i_feedback.text.toString().trim()

        if(user_feedback.isEmpty()){
            i_feedback.error = "Please enter your feedback"
            return
        }

        val feedbackID = ref.push().key.toString()
        val feedback = Feedback( feedbackID, user_feedback, i_ratingBar.rating.toInt())

        ref.child(feedbackID).setValue(feedback).addOnCompleteListener {
            Toast.makeText(context, "Feedback added successfully", Toast.LENGTH_SHORT).show()
        }

    }
}