package com.example.aicte_app

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aicte_app.daos.CollegeDao
import com.example.aicte_app.models.College
import com.example.aicte_app.viewHolder.CollegeViewHolder
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.Query

class SearchCollegesActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var collegeDao: CollegeDao

    private lateinit var year:String
    private lateinit var level:String
    private lateinit var type:String
    private lateinit var state:String
    private lateinit var program:String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_colleges)

        val bundle = intent.extras

        if (bundle != null) {
            year = bundle.getString("year")!!
            level = bundle.getString("level")!!
            type = bundle.getString("type")!!
            program = bundle.getString("program")!!
            state = bundle.getString("state")!!


        }

        recyclerView=findViewById(R.id.clg_recycler_view)
        recyclerView.setHasFixedSize(true)
        layoutManager= LinearLayoutManager(this)
        recyclerView.layoutManager=layoutManager

        if(year!=null && level!=null && state!=null && program!=null && type!=null)
            searchCollegesWithFilters(year,level,state,program,type)

    }




    private fun searchCollegesWithFilters(year: String, level: String, state: String, program: String, type: String) {

        collegeDao = CollegeDao()
        val collegeCollections = collegeDao.collegeCollections

        val query:Query=collegeCollections.whereEqualTo("state",state).whereEqualTo("type",type).whereEqualTo("program",program)
            .whereEqualTo("year",year).whereEqualTo("level",level)

        val options: FirestoreRecyclerOptions<College> = FirestoreRecyclerOptions.Builder<College>()
            .setQuery(query, College::class.java)
            .build()


        val adapter: FirestoreRecyclerAdapter<College, CollegeViewHolder> = object :
            FirestoreRecyclerAdapter<College, CollegeViewHolder>(options)
        {
            override fun onBindViewHolder(holder: CollegeViewHolder, p1: Int, model: College)
            {
                holder.clgName.text = model.name
//                if(model.state!![0]!="[--All--]")
//                    holder.clgstate.text = model.state[0]
//                else
//                    holder.clgstate.text = model.state[1]


            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollegeViewHolder {
                val view: View = LayoutInflater.from(parent.context).inflate(R.layout.colleges_item_view, parent, false)
                return CollegeViewHolder(view)
            }
        }

        recyclerView.adapter = adapter
        adapter.startListening()
    }


}