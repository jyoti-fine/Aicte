package com.example.aicte_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aicte_app.daos.UnApprovedCollegeDao
import com.example.aicte_app.models.SchemeInitiative
import com.example.aicte_app.models.UnApprovedCollege
import com.example.aicte_app.viewHolder.SchemeViewHolder
import com.example.aicte_app.viewHolder.UnApprovedCollegeViewHolder
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.squareup.picasso.Picasso

class UnApprovedCollegeActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var unApprovedCollegeDao: UnApprovedCollegeDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_un_approved_college)

        recyclerView=findViewById(R.id.unapproved_recycler_view)
        recyclerView.setHasFixedSize(true)
        layoutManager= LinearLayoutManager(this)
        recyclerView.layoutManager=layoutManager

        setUpRecyclerView()
    }

    private fun setUpRecyclerView()
    {
        unApprovedCollegeDao = UnApprovedCollegeDao()
        val unApprovedClgCollections = unApprovedCollegeDao.unApprovedCollegeCollections

        val options: FirestoreRecyclerOptions<UnApprovedCollege> = FirestoreRecyclerOptions.Builder<UnApprovedCollege>()
            .setQuery(unApprovedClgCollections, UnApprovedCollege::class.java)
            .build()


        val adapter: FirestoreRecyclerAdapter<UnApprovedCollege, UnApprovedCollegeViewHolder> = object :
            FirestoreRecyclerAdapter<UnApprovedCollege, UnApprovedCollegeViewHolder>(options)
        {
            override fun onBindViewHolder(holder: UnApprovedCollegeViewHolder, p1: Int, model: UnApprovedCollege)
            {
                holder.unApprovedClgName.text = model.name
                holder.unApprovedClgstate.text = model.state


            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UnApprovedCollegeViewHolder {
                val view: View = LayoutInflater.from(parent.context).inflate(R.layout.colleges_item_view, parent, false)
                return UnApprovedCollegeViewHolder(view)
            }
        }

        recyclerView.adapter = adapter
        adapter.startListening()
    }
}