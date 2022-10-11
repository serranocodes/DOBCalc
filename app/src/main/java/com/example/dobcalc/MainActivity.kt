package com.example.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate: TextView? = null
    private var tvAgeInDays: TextView? = null
    private var tvAgeInMinutes: TextView? = null
    private var tvAgeInSeconds: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInDays = findViewById((R.id.tvAgeInDays))
        tvAgeInMinutes = findViewById((R.id.tvAgeInMinutes))
        tvAgeInSeconds = findViewById((R.id.tvAgeInSeconds))

        btnDatePicker.setOnClickListener {

            clickDatePicker()

        }
    }

    fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        var year = myCalendar.get(Calendar.YEAR)
        var month = myCalendar.get(Calendar.MONTH)
        var day = myCalendar.get(Calendar.DAY_OF_MONTH)
        var dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{view, selectedYear, selectedMonth, selectedDayOfMonth ->

                Toast.makeText(this, "EAT LESS LIVE MORE!",
                    Toast.LENGTH_LONG)

                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                tvSelectedDate?.setText(selectedDate)

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)

                theDate?.let{
                    val selectedDateInDays = theDate.time / (60000*60*24)
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                    currentDate?.let{
                        val currentDateInDays = currentDate.time/ (60000*60*24)
                        val differenceInDays = currentDateInDays - selectedDateInDays

                        tvAgeInDays?.text = differenceInDays.toString()
                        tvAgeInMinutes?.text = (differenceInDays*24*60).toString()
                        tvAgeInSeconds?.text = (differenceInDays*24*60*60).toString()
                    }

                }

            }, year, month, day)


        dpd.datePicker.maxDate = System.currentTimeMillis() - 8640000
        dpd.show()








    }

}