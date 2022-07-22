package com.example.foodorder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodorder.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener { calculateBill() }
    }

    private fun calculateBill(){
        var sum = 0
        var switch: Boolean = false

        if (binding.checkBox1.isChecked){
            sum += 1200
        }
        if(binding.checkBox2.isChecked){
            sum += 400
        }
        if(binding.checkBox3.isChecked){
            sum += 600
        }
        if(binding.checkBox4.isChecked){
            sum += 120
        }
        if (binding.switch1.isChecked){
            sum += 120
            switch = true
        }

        val sumTotal = "$sum"

        val intent = Intent(this,TipTotal::class.java)
        intent.putExtra("totalCost",sumTotal)
        intent.putExtra("switch",switch)
        startActivity(intent)
    }


}