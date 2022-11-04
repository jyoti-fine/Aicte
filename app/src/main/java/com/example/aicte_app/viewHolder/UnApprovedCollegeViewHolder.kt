package com.example.aicte_app.viewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aicte_app.R
import com.example.aicte_app.`interface`.ItemClickListener

class UnApprovedCollegeViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    var unApprovedClgName: TextView =itemView.findViewById(R.id.college_name)
    var unApprovedClgstate: TextView =itemView.findViewById(R.id.college_state)


    var listner: ItemClickListener? = null
    override fun onClick(p0: View?) {

    }

    fun setItemClickListner(listner: ItemClickListener?) {
        this.listner = listner
    }

}