package com.example.aicte_app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class EntrepreneurActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var scheme:String
    private lateinit var spinnerChoose : Spinner

    private lateinit var adapterChoose : ArrayAdapter<CharSequence>

    private lateinit var choice:String
    private lateinit var domain:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrepreneur)

        val spinnerFeature : Spinner =findViewById(R.id.spinner_enter1)
        var adapterFeature : ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(this, R.array.enterScheme,
            R.layout.spinner_item_view)

        adapterFeature.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinnerFeature.adapter=adapterFeature
        spinnerFeature.onItemSelectedListener = this

        spinnerChoose =findViewById(R.id.spinner_enter2)

        domain="Agricultural Development"

        val btn: Button =findViewById(R.id.button_submit_enter)
        btn.setOnClickListener{

            val bundle = Bundle()



            bundle.putString("choice",choice)!!
            bundle.putString("domain",domain)!!

            val intent = Intent(this@EntrepreneurActivity,GrantAdminActivity::class.java)
            intent.putExtras(bundle);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finish()

        }


    }

    override fun onItemSelected(parent: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

        if (parent != null) {
            when(parent.id)
            {
                R.id.spinner_enter1->{

                    scheme=parent.getItemAtPosition(p2).toString()

                    choice=scheme

                    when(scheme)
                    {
                        "Free Courses for Enterpreneurship"->{



                             adapterChoose =ArrayAdapter.createFromResource(this, R.array.enterChoice1,
                                R.layout.spinner_item_view)

                        }

                        "Apply for Grant"->{



                             adapterChoose  = ArrayAdapter.createFromResource(this, R.array.enterChoice2,
                                R.layout.spinner_item_view)

                        }

                        "Mentorship"->{



                            adapterChoose = ArrayAdapter.createFromResource(this, R.array.enterChoice3,
                                R.layout.spinner_item_view)

                        }

                        "Contact to Incubation"->{



                            adapterChoose  = ArrayAdapter.createFromResource(this, R.array.enterChoice4,
                                R.layout.spinner_item_view)

                        }

                    }

                    adapterChoose.setDropDownViewResource(android.R.layout.simple_spinner_item)
                    spinnerChoose.adapter=adapterChoose


                }


                R.id.spinner_enter2->{
                      domain=parent.getItemAtPosition(p2).toString()


                }

            }
        }

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}