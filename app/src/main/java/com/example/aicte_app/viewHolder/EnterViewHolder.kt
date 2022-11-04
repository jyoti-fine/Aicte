package com.example.aicte_app.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aicte_app.R
import com.example.aicte_app.`interface`.ItemClickListener

class EnterViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    var enterName: TextView =itemView.findViewById(R.id.enter_title)
    var enterAmount: TextView =itemView.findViewById(R.id.enter_amount)


    var listner: ItemClickListener? = null
    override fun onClick(p0: View?) {

    }

    fun setItemClickListner(listner: ItemClickListener?) {
        this.listner = listner
    }


}