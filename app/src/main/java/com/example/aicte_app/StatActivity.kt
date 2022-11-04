package com.example.aicte_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.utils.ColorTemplate

class StatActivity : AppCompatActivity() {

    private lateinit var barChart: BarChart
    private lateinit var pieChart: PieChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stat)


        //assigning varible

        //assigning varible
        barChart = findViewById(R.id.bar_chart)
        pieChart = findViewById(R.id.pie_chart)


        //initializa array list


        //initializa array list
        val barEntries = ArrayList<BarEntry>()
        val pieEntries = ArrayList<PieEntry>()
        //use for loop
        //use for loop
        for (i in 1..9) {
            //convert to float
            val value = (i * 10.0).toFloat()
            //initializa bar chart entry
            val barEntry = BarEntry(i.toFloat(), value)
            //initialize pie chart entry
            val pieEntry = PieEntry(i.toFloat(), value)
            //add values in array list
            barEntries.add(barEntry)
            pieEntries.add(pieEntry)
        }
        //initialize bar data set
        //initialize bar data set
        val barDataSet = BarDataSet(barEntries, "Employees")
        //set colors
        //set colors
        barDataSet.setColors(*ColorTemplate.COLORFUL_COLORS)
        //hide draw value
        //hide draw value
        barDataSet.setDrawValues(false)
        //set bar data
        //set bar data
        barChart.data = BarData(barDataSet)
        //set animation
        //set animation
        barChart.animateY(5000)
        //set description text and colors
        //set description text and colors
        barChart.description.text = "Employees Chart"
        barChart.description.textColor = android.R.color.holo_blue_light

        //initialize pie data set

        //initialize pie data set
        val pieDataSet = PieDataSet(pieEntries, "Student")
        //set color
        //set color
        pieDataSet.setColors(*ColorTemplate.COLORFUL_COLORS)
        //set pie data
        //set pie data
        pieChart.setData(PieData(pieDataSet))
        //set animation
        //set animation
        pieChart.animateXY(5000, 5000)
        //hide description
        //hide description
        pieChart.getDescription().isEnabled = false
    }
}