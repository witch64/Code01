package com.example.code00.Feedback

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.code00.R

class FeedbackAdapter(val mCtx: Context,val layoutResId: Int,val feedbackList: List<Feedback>) :
    ArrayAdapter<Feedback>(mCtx, layoutResId, feedbackList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater : LayoutInflater = LayoutInflater.from(mCtx)
        val view : View = layoutInflater.inflate(layoutResId,null)

        val textView_feedback = view.findViewById<TextView>(R.id.textView_feedback)

        val feedbacks = feedbackList[position]

        textView_feedback.text = feedbacks.feedback

        return view
    }


}