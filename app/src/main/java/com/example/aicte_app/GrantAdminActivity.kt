package com.example.aicte_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aicte_app.daos.EnterDao
import com.example.aicte_app.daos.InitiativesSchemesDao
import com.example.aicte_app.models.Enter
import com.example.aicte_app.models.SchemeInitiative
import com.example.aicte_app.viewHolder.EnterViewHolder
import com.example.aicte_app.viewHolder.SchemeViewHolder
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.squareup.picasso.Picasso

class GrantAdminActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var enterDao: EnterDao

    private lateinit var choice:String
    private lateinit var domain:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grant_admin)

        val bundle = intent.extras

        if (bundle != null) {
            choice = bundle.getString("choice")!!
            domain = bundle.getString("domain")!!

        }

        recyclerView=findViewById(R.id.enter_recycler_view)
        recyclerView.setHasFixedSize(true)
        layoutManager= LinearLayoutManager(this)
        recyclerView.layoutManager=layoutManager
    }

    override fun onStart() {
        super.onStart()

        setUpRecyclerView()

    }

    private fun setUpRecyclerView() {
        enterDao = EnterDao()
        val enterCollections = enterDao.enterCollections

        val query = enterCollections.whereEqualTo("choice",choice).whereEqualTo("domain",domain)

        val options: FirestoreRecyclerOptions<Enter> = FirestoreRecyclerOptions.Builder<Enter>()
            .setQuery(query, Enter::class.java)
            .build()


        val adapter: FirestoreRecyclerAdapter<Enter, EnterViewHolder> = object :
            FirestoreRecyclerAdapter<Enter, EnterViewHolder>(options)
        {
            override fun onBindViewHolder(holder: EnterViewHolder, p1: Int, model: Enter)
            {
                holder.enterName.text = model.name
                holder.enterAmount.text = model.amount




            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EnterViewHolder {
                val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_view_recycler_enter, parent, false)
                return EnterViewHolder(view)
            }
        }

        recyclerView.adapter = adapter
        adapter.startListening()
    }


}