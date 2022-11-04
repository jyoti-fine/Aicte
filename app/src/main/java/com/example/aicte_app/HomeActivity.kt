package com.example.aicte_app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.aicte_app.databinding.ActivityHomeBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import io.paperdb.Paper





class HomeActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeBinding


    private lateinit var nam: TextView
    private lateinit var nam2: TextView
    private lateinit var nam3: TextView
    private lateinit var nam4: TextView
    private lateinit var nam5: TextView
    private lateinit var nam6: TextView


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

//        nam = findViewById(R.id.textView3);
//        nam2 = findViewById(R.id.textView6);
//        nam3 = findViewById(R.id.textView7);
//        nam4= findViewById(R.id.textView8);
//        nam5 = findViewById(R.id.textView4);
//        nam6 = findViewById(R.id.textView5);
//
//
//
//        nam.setOnClickListener {
//
//            gotoUrl("https://parakh.aicte-india.org/");
//
//        }
//
//        nam3.setOnClickListener {
//
//                gotoUrl("https://drive.aicte-india.org/yoga/");
//            }
//
//        nam2.setOnClickListener {
//
//                gotoUrl("https://free.aicte-india.org/");
//
//        }

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarHome.toolbar)

        binding.appBarHome.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_home)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navView.setNavigationItemSelectedListener {

            val id = it.itemId

            if (id == R.id.nav_home) {
                val intent = Intent(this@HomeActivity, HomeActivity::class.java)
                startActivity(intent)

            }
            else if(id == R.id.nav_statistics)
            {
                val intent = Intent(this@HomeActivity, MainActivity::class.java)
                startActivity(intent)
            }
            else if(id == R.id.nav_schemes)
            {
                val intent = Intent(this@HomeActivity, SchemesActivity::class.java)
                startActivity(intent)
            }
            else if(id == R.id.nav_initiatives)
            {
                val intent = Intent(this@HomeActivity, InitiativesActivity::class.java)
                startActivity(intent)
            }
            else if(id == R.id.nav_rules_regulations)
            {
                val intent = Intent(this@HomeActivity, RulesAndRegulationsActivity::class.java)
                startActivity(intent)
            }
            else if(id == R.id.nav_setting)
            {
                val intent = Intent(this@HomeActivity, UserResetPasswordActivity::class.java)
                intent.putExtra("check","settings")
                startActivity(intent)
            }
            else if(id == R.id.nav_about)
            {
                val intent = Intent(this@HomeActivity,AboutActivity::class.java)
                startActivity(intent)

            }
            else if(id == R.id.nav_logout)
            {
                Paper.book().destroy()
                val intent = Intent(this@HomeActivity, UserCredentialActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
            }
            else if(id == R.id.nav_entrepreneur)
            {
                val intent = Intent(this@HomeActivity,EntrepreneurActivity::class.java)
                startActivity(intent)
            }


            val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
            drawer.closeDrawer(GravityCompat.START)
            return@setNavigationItemSelectedListener true
        }

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { view ->


            val intent = Intent(this@HomeActivity, ChatBotActivity::class.java)
            startActivity(intent)
        }




    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_home)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    private fun gotoUrl(s: String) {
        val uri: Uri = Uri.parse(s)
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }

}
