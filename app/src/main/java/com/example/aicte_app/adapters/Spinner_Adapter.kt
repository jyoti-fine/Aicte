package com.example.aicte_app.adapters

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView


class Spinner_Adapter:BaseAdapter {

    private lateinit var context: Context
    private lateinit var itemList:List<String>


    constructor(context: Context, itemList: List<String>)
    {
        this.context=context
        this.itemList=itemList
    }

    override fun getCount(): Int {
        return itemList?.size ?: 0
    }

    override fun getItem(i: Int): Any {
        return i
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getView(i: Int, view: View, viewGroup: ViewGroup): View {

        val rootView: View = LayoutInflater.from(context)
            .inflate(R.layout., viewGroup, false)
        val txtName = rootView.findViewById<TextView>(R.id.option)
        txtName.text = itemList[i]

        return rootView


    }
}
