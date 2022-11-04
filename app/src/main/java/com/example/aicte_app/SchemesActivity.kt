package com.example.aicte_app

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isInvisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aicte_app.daos.InitiativesSchemesDao
import com.example.aicte_app.models.SchemeInitiative
import com.example.aicte_app.viewHolder.SchemeViewHolder
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.squareup.picasso.Picasso

class SchemesActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var initiativesSchemesDao: InitiativesSchemesDao

    private lateinit var schemeTitle:TextView

    private lateinit var scheme:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schemes)

        val spinnerYear : Spinner =findViewById(R.id.spinner_schemes)
        var adapter : ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(this, R.array.schemes,
            R.layout.spinner_item_view)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinnerYear.adapter=adapter
        spinnerYear.onItemSelectedListener = this

        schemeTitle=findViewById(R.id.scheme_title)



        recyclerView=findViewById(R.id.scheme_recycler_view)
        recyclerView.setHasFixedSize(true)
        layoutManager= LinearLayoutManager(this)
        recyclerView.layoutManager=layoutManager

        val schemeBtn:Button=findViewById(R.id.scheme_search_btn)

        schemeBtn.setOnClickListener{

            schemeTitle.text = scheme
            schemeTitle.visibility=View.VISIBLE

           setUpRecyclerView()


        }
    }



    private fun setUpRecyclerView()
    {

        initiativesSchemesDao = InitiativesSchemesDao()
        val initiativesSchemesCollections = initiativesSchemesDao.intiative_SchemeCollections

        val query = initiativesSchemesCollections.whereEqualTo("type",scheme).whereEqualTo("domain","schemes")

        val options: FirestoreRecyclerOptions<SchemeInitiative> = FirestoreRecyclerOptions.Builder<SchemeInitiative>()
            .setQuery(query, SchemeInitiative::class.java)
            .build()


        val adapter: FirestoreRecyclerAdapter<SchemeInitiative, SchemeViewHolder> = object :
            FirestoreRecyclerAdapter<SchemeInitiative, SchemeViewHolder>(options)
        {
            override fun onBindViewHolder(holder: SchemeViewHolder, p1: Int, model: SchemeInitiative)
            {
                holder.initiativeSchemeName.text = model.name
                Picasso.get().load(model.image).into(holder.initiativeSchemeImage)

                holder.itemView.setOnClickListener{
                    gotoUrl(model.link);
                }


            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchemeViewHolder {
                val view: View = LayoutInflater.from(parent.context).inflate(R.layout.scheme_item_view, parent, false)
                return SchemeViewHolder(view)
            }
        }

        recyclerView.adapter = adapter
        adapter.startListening()

    }

    override fun onItemSelected(parent: AdapterView<*>?, p1: View?, p2: Int, p3: Long)
    {

        if (parent != null)
        {
            when(parent.id)
            {
                R.id.spinner_schemes ->
                {

                    scheme = parent.getItemAtPosition(p2).toString()


                }
            }
        }

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    private fun gotoUrl(s: String) {
        val uri: Uri = Uri.parse(s)
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }


}