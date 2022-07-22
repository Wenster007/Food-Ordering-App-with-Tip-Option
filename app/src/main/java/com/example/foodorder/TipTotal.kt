package com.example.foodorder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import com.example.foodorder.databinding.ActivityTipTotalBinding
import java.text.NumberFormat
import kotlin.math.ceil

class TipTotal : AppCompatActivity() {

    private lateinit var binding: ActivityTipTotalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTipTotalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val totalCost:String = intent.getStringExtra("totalCost").toString()
        binding.costOfServiceEditText.setText(totalCost)

        binding.finalTotal.visibility = INVISIBLE

        binding.calculateButton.setOnClickListener {
            calculateTip()
        }
    }

    private fun calculateTip() {

        val stringInTextField = binding.costOfServiceEditText.text.toString() //here it was first editable type so toDouble() method
        //was not running so we had to convert it into String.

        val cost = stringInTextField.toDoubleOrNull()

        if (cost == null){ //We used this so that incase if field was entered empty so we can return from here.
            binding.tipResult.text = ""
            return
        }

        val selectedId = binding.tipOptions.checkedRadioButtonId //here we are getting the id of the button from the radio
        //group which is selected and saving it into a variable.


        val tipPercentage = when (selectedId){    //Here we created a variable to store the percentage and from the id that
            R.id.option_twenty_percent -> 0.1    //we got in the last code we used an if else to give it correct values
            R.id.option_eighteen_percent -> 0.05  //based on the id .
            else -> 0.0
        }

        binding.viewFoodCost.text = "Food Cost : $${cost.toString()}"

        var tip = cost*tipPercentage

        val roundup = binding.roundUpSwitch.isChecked

        if (roundup){
            tip = ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)

        binding.finalTotal.visibility = VISIBLE
        binding.finalTotal.text = "Total Bill : $${(cost+tip).toString()}"
    }
}