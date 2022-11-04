package com.example.aicte_app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aicte_app.daos.RulesRegulationsDao
import com.example.aicte_app.models.RulesRegulations
import com.example.aicte_app.viewHolder.RulesRegulationsViewHolder
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions


class RulesAndRegulationsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var rulesAndRegulationsDao: RulesRegulationsDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rules_and_regulations)

        recyclerView=findViewById(R.id.recyclerViewRules)
        recyclerView.setHasFixedSize(true)
        layoutManager= LinearLayoutManager(this)
        recyclerView.layoutManager=layoutManager

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        rulesAndRegulationsDao = RulesRegulationsDao()
        val rulesRegulationCollections = rulesAndRegulationsDao.rulesRegulationsCollections

        val options: FirestoreRecyclerOptions<RulesRegulations> = FirestoreRecyclerOptions.Builder<RulesRegulations>()
            .setQuery(rulesRegulationCollections, RulesRegulations::class.java)
            .build()


        val adapter: FirestoreRecyclerAdapter<RulesRegulations, RulesRegulationsViewHolder> = object :
            FirestoreRecyclerAdapter<RulesRegulations,  RulesRegulationsViewHolder>(options)
        {
            override fun onBindViewHolder(holder:  RulesRegulationsViewHolder, p1: Int, model: RulesRegulations)
            {
                holder.rulesTitle.text = model.title

                holder.itemView.setOnClickListener{
                    gotoUrl(model.link);
                }

            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RulesRegulationsViewHolder {
                val view: View = LayoutInflater.from(parent.context).inflate(R.layout.rules_regulations_item_view, parent, false)
                return RulesRegulationsViewHolder(view)
            }
        }

        recyclerView.adapter = adapter
        adapter.startListening()
    }

    private fun gotoUrl(s: String) {
        val uri: Uri = Uri.parse(s)
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }
}