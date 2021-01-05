package com.example.ageinminutes

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.btnDatePicker) as Button
        btn.setOnClickListener { view ->
            clickDatePicker(view)
        }
    }


    @SuppressLint("WrongViewCast", "SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.N)
    fun clickDatePicker(view: View) {

        val tvSD = findViewById<TextView>(R.id.tvSelectedDate)
        val tvSDIM = findViewById<TextView>(R.id.tvSelectedDateInMinutes)
        val myCalander = Calendar.getInstance()
        val year = myCalander.get(Calendar.YEAR)
        val month = myCalander.get(Calendar.MONTH)
        val dayOfMonth = myCalander.get(Calendar.DAY_OF_MONTH)


        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener
        { view, SelectedYear, SelectedMonth, SelectedDayOfMonth ->
            Toast.makeText(this,
                    "The chosen year: $SelectedYear The chosen month: $SelectedMonth The chosen day: $SelectedDayOfMonth",
                    Toast.LENGTH_LONG).show()

            val selectedDate = "$SelectedYear/${SelectedMonth + 1}/$SelectedDayOfMonth"
            val sdf = SimpleDateFormat("DD/MM/YYYY")
            val theDate = sdf.parse(selectedDate)

            val selectedDateInMinutes = theDate!!.time / 60000
            tvSD.setText(theDate.toString())


            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInMinutes = currentDate!!.time / 60000


            val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
            tvSDIM.setText(differenceInMinutes.toString())

        }, year, month, dayOfMonth)
        dpd.datePicker.setMaxDate(Date().time - 86400000)

        dpd.show()
    }
}