package com.example.aicte_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aicte_app.daos.CollegeDao
import com.example.aicte_app.models.Colleges
import com.example.aicte_app.viewHolder.CollegeViewHolder
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.Query


class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var year:String
    private lateinit var level:String
    private lateinit var type:String
    private lateinit var state:String
    private lateinit var program:String

    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var collegeDao: CollegeDao



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView=findViewById(R.id.clg_recycler_view)
        recyclerView.setHasFixedSize(true)
        layoutManager= LinearLayoutManager(this)
        recyclerView.layoutManager=layoutManager


        val spinnerYear: Spinner =findViewById(R.id.spinner_year)
        var adapter : ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(this,
            R.array.year,android.R.layout.simple_spinner_dropdown_item)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinnerYear.adapter=adapter
        spinnerYear.onItemSelectedListener = this

        val spinnerLevel: Spinner =findViewById(R.id.spinner_level)
        adapter = ArrayAdapter.createFromResource(this, R.array.level,
            android.R.layout.simple_spinner_dropdown_item)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinnerLevel.adapter=adapter
        spinnerLevel.onItemSelectedListener = this


        val spinnerState: Spinner =findViewById(R.id.spinner_state)
        adapter = ArrayAdapter.createFromResource(this, R.array.state,
            android.R.layout.simple_spinner_dropdown_item)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinnerState.adapter=adapter
        spinnerState.onItemSelectedListener = this


        val spinnerProgram: Spinner =findViewById(R.id.spinner_program)
        adapter = ArrayAdapter.createFromResource(this, R.array.program,
            android.R.layout.simple_spinner_dropdown_item)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinnerProgram.adapter=adapter
        spinnerProgram.onItemSelectedListener = this

        val spinnerType: Spinner =findViewById(R.id.spinner_type)
        adapter = ArrayAdapter.createFromResource(this, R.array.type,
            android.R.layout.simple_spinner_dropdown_item)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinnerType.adapter=adapter
        spinnerType.onItemSelectedListener = this

        val searchBtn: Button =findViewById(R.id.clg_search_btn)

        searchBtn.setOnClickListener{
            searchColleges()
        }


    }



    private fun searchColleges() {

        if(year!=null)
        {
            if(level!=null)
            {
                if(state!=null)
                {
                    if(program!=null)
                    {
                        if(type!=null)
                        {
                            searchCollegesWithFilters(year,level,state,program,type)
                        }
                        else
                        {
                            Toast.makeText(this,"Please Select the Type",Toast.LENGTH_LONG).show()
                        }
                    }
                    else
                    {
                        Toast.makeText(this,"Please Select the Program",Toast.LENGTH_LONG).show()
                    }
                }
                else
                {
                    Toast.makeText(this,"Please Select the State",Toast.LENGTH_LONG).show()
                }
            }
            else
            {
                Toast.makeText(this,"Please Select the Level",Toast.LENGTH_LONG).show()
            }
        }
        else
        {
            Toast.makeText(this,"Please Select the Year",Toast.LENGTH_LONG).show()
        }
    }

    private fun searchCollegesWithFilters(year: String, level: String, state: String, program: String, type: String) {

        collegeDao = CollegeDao()
        val collegeCollections = collegeDao.collegeCollections

        val query :Query = collegeCollections.whereEqualTo("year",year).whereEqualTo("state",state)


        val options: FirestoreRecyclerOptions<Colleges> = FirestoreRecyclerOptions.Builder<Colleges>()
            .setQuery(query, Colleges::class.java)
            .build()


        val adapter: FirestoreRecyclerAdapter<Colleges, CollegeViewHolder> = object :FirestoreRecyclerAdapter<Colleges, CollegeViewHolder>(options)
        {
            override fun onBindViewHolder(holder: CollegeViewHolder, p1: Int, model: Colleges)
            {
                holder.clgName.text = model.name
                holder.clgstate.text = model.state
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollegeViewHolder {
                val view:View = LayoutInflater.from(parent.context).inflate(R.layout.colleges_item_view, parent, false)
                return CollegeViewHolder(view)
            }
        }

        recyclerView.adapter = adapter
        adapter.startListening()
    }

    override fun onStart() {
        super.onStart()

        setUpRecyclerView()

    }

    private fun setUpRecyclerView()
    {

        collegeDao = CollegeDao()
        val collegeCollections = collegeDao.collegeCollections

        val query = collegeCollections.orderBy("year", Query.Direction.DESCENDING)


        val options: FirestoreRecyclerOptions<Colleges> = FirestoreRecyclerOptions.Builder<Colleges>()
            .setQuery(query, Colleges::class.java)
            .build()


        val adapter: FirestoreRecyclerAdapter<Colleges, CollegeViewHolder> = object :FirestoreRecyclerAdapter<Colleges, CollegeViewHolder>(options)
        {
            override fun onBindViewHolder(holder: CollegeViewHolder, p1: Int, model: Colleges)
            {
                holder.clgName.text = model.name
                holder.clgstate.text = model.state
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollegeViewHolder {
                val view:View = LayoutInflater.from(parent.context).inflate(R.layout.colleges_item_view, parent, false)
                return CollegeViewHolder(view)
            }
        }

        recyclerView.adapter = adapter
        adapter.startListening()

    }

    override fun onStop() {
        super.onStop()
    }

    override fun onItemSelected(parent: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        if (parent != null) {
            when(parent.id)
            {
                R.id.spinner_year->{

                    year=parent.getItemAtPosition(p2).toString()

                }
                R.id.spinner_type->{
                    type=parent.getItemAtPosition(p2).toString()

                }

                R.id.spinner_state->{
                    state=parent.getItemAtPosition(p2).toString()

                }

                R.id.spinner_program->{
                    program=parent.getItemAtPosition(p2).toString()
                }

                R.id.spinner_level->{

                    level=parent.getItemAtPosition(p2).toString()

                }

            }
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }


}