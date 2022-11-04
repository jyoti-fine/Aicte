package com.example.aicte_app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var year:String
    private lateinit var level:String
    private lateinit var type:String
    private lateinit var state:String
    private lateinit var program:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val spinnerYear :Spinner=findViewById(R.id.spinner_year)
        var adapter : ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(this, R.array.year,
           R.layout.spinner_item_view)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinnerYear.adapter=adapter
        spinnerYear.onItemSelectedListener = this


        val spinnerLevel: Spinner =findViewById(R.id.spinner_level)
        adapter = ArrayAdapter.createFromResource(this, R.array.level,
            R.layout.spinner_item_view)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinnerLevel.adapter=adapter
        spinnerLevel.onItemSelectedListener = this


        val spinnerState: Spinner =findViewById(R.id.spinner_state)
        adapter = ArrayAdapter.createFromResource(this, R.array.state,
            R.layout.spinner_item_view)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinnerState.adapter=adapter
        spinnerState.onItemSelectedListener = this


        val spinnerProgram: Spinner =findViewById(R.id.spinner_program)
        adapter = ArrayAdapter.createFromResource(this, R.array.program,
            R.layout.spinner_item_view)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinnerProgram.adapter=adapter
        spinnerProgram.onItemSelectedListener = this

        val spinnerType: Spinner =findViewById(R.id.spinner_type)
        adapter = ArrayAdapter.createFromResource(this, R.array.type,
            R.layout.spinner_item_view)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinnerType.adapter=adapter
        spinnerType.onItemSelectedListener = this

        val searchBtn: Button =findViewById(R.id.clg_search_btn)

        searchBtn.setOnClickListener{

            val bundle = Bundle()

            bundle.putString("year",year)
            bundle.putString("level",level)
            bundle.putString("type",type)
            bundle.putString("state",state)
            bundle.putString("program",program)

            val intent = Intent(this@MainActivity,SearchCollegesActivity::class.java)
            intent.putExtras(bundle);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finish()

        }

        val statBtn: Button =findViewById(R.id.clg_stat)

        statBtn.setOnClickListener{
            val intent = Intent(this@MainActivity, StatActivity::class.java)
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finish()
        }

        val unApprovedBtn: Button = findViewById(R.id.clg_unapproved)
        unApprovedBtn.setOnClickListener{
            val intent = Intent(this@MainActivity, UnApprovedCollegeActivity::class.java)
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finish()

        }


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