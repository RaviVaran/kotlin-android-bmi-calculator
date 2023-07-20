package com.example.bmi_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val weightText = findViewById<EditText>(R.id.etWeight)
        val heightText = findViewById<EditText>(R.id.etHeight)
        val calcButtion = findViewById<Button>(R.id.btncalculate)
        calcButtion.setOnClickListener {
            val weight = weightText.text.toString()
            val height = heightText.text.toString()
            if (validateInput( weight,height )) {
                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
                // get the result in two decimal places
                val bmi2digits = String.format("%.2f", bmi).toFloat()
                displayResult(bmi2digits)
            }

    }

    }
    private fun validateInput(weight:String?,Height:String?):Boolean{
        return when{
            weight.isNullOrEmpty()  ->{
                Toast.makeText(this,"Weight is empty",Toast.LENGTH_LONG).show()
                return false
            }
            Height.isNullOrEmpty()  ->{
                Toast.makeText(this,"Height is empty",Toast.LENGTH_LONG).show()
                return false
            }
            else ->{
                return true
            }
        }
    }
    private fun displayResult(bmi:Float){
        val resultIndex = findViewById<TextView>(R.id.tvIndex)
        val resultDescription = findViewById<TextView>(R.id.tvResult)
        val info = findViewById<TextView>(R.id.tvInfo)
        resultIndex.text = bmi.toString()
        info.text = "(normal range is 18.5 - 24.9)"

        var resultText = ""
        var color = 0

        when{
            bmi <18.5->{
                resultText = "under weight"
                color = R.color.underweight
                       }
          bmi in 18.5..24.99->{
              resultText = "Healthy"
              color = R.color.narmal
                               }
            bmi in 25.0..29.99->{
                resultText = "overweight"
                color = R.color.over_weight
                                }
            bmi > 29.99->{
                resultText = "obes"
                color = R.color.obes
            }
            }

        resultDescription.setTextColor(ContextCompat.getColor(this, color))
        resultDescription.text = resultText

    }

}