package com.example.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_bmi_calculator.*
import java.math.BigDecimal
import java.math.RoundingMode

class bmi_calculator : AppCompatActivity() {
    private var bmi : Float = 0F
    private var result : String? = null
    private var description : String? = null
    val Metric_View = "Metric_VIew"
    val Us_View = "Us_View"
    var currentVisibleView : String = Metric_View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi_calculator)
        setSupportActionBar(toolbar_bmi_calculator)
        val actionbar = supportActionBar
        if(actionbar != null){
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.title = "BMI CALCULATOR"
        }
        toolbar_bmi_calculator.setNavigationOnClickListener {
            onBackPressed()
        }
        bt_calculate.setOnClickListener{
            ll_invisibe_layout.setVisibility(View.GONE)
            if(currentVisibleView.equals(Metric_View)){
                if(!validateMetric()){
                    Toast.makeText(this,"Please enter Valid values",Toast.LENGTH_SHORT).show()

                }else{
                    val wt = et_weight_kg.text.toString().toFloat()
                    val ht = et_height_cm.text.toString().toFloat()/100
                    bmi =  BigDecimal((wt/(ht*ht)).toDouble()).setScale(2,RoundingMode.HALF_EVEN).toFloat()

                    showResult(bmi)

                }
            }else{
                if(validateUsunits()){
                    val wt = et_weight_lbs.text.toString().toFloat()
                    val ht =( et_height_inch.text.toString().toFloat() +et_height_feet.text.toString().toFloat()*12)
                    bmi = BigDecimal((703*(wt/(ht*ht))).toDouble()).setScale(2,RoundingMode.HALF_EVEN).toFloat()
                    showResult(bmi)
                }else{
                    Toast.makeText(this,"Please enter Valid values",Toast.LENGTH_SHORT).show()
                }
            }

        }
        setMetricsystem()
        rgunits.setOnCheckedChangeListener { group, checkedId ->
            ll_invisibe_layout.setVisibility(View.GONE)
            if(checkedId == R.id.rb_metric){
                setMetricsystem()
            }else{
                setUssystem()
            }
        }
    }
    private fun showResult(bmi : Float){
        if(bmi.compareTo(16f)<=0){
            result = "severely underweight"
            description = "You are underweight , Please act now. Eat more!"
        }else if(bmi.compareTo(16f)>0 && bmi.compareTo(18.5f)<=0){
            result = "Underweight"
            description = "You are underweight , Please act now. Eat more!"
        }else if(bmi.compareTo(18.5f)>0 && bmi.compareTo(25f)<0){
            result = "Healthy"
            description = "You Lucky duck!, you are in good shape"
        }else if(bmi.compareTo(25f)>0 && bmi.compareTo(30f)<0){
            result = "Overweight"
            description = "You need more exercise and control on your eating habits"
        }else if(bmi.compareTo(30f)>0 && bmi.compareTo(35f)<0){
            result = "Obese"
            description = "Obesity is not good! Please act now"
        }else if(bmi.compareTo(35f)>0 && bmi.compareTo(40f)<0){
            result = "Severely Obese"
            description = "This does not look good please consult an expert :)"
        }else {
            result = "Severely  Obese II"
            description = "Sorry to say but you are obese even with the standard of normal obese person"
        }
        tv_show_bmi.text = bmi.toString()
        tv_result.text = result
        tv_description.text = description
        ll_invisibe_layout.visibility  = View.VISIBLE
    }

    private fun setMetricsystem(){
        currentVisibleView = Metric_View
        ll_bmi_calculator_layout.visibility = View.VISIBLE
        ll_bmi_calculator_layout_us.visibility = View.GONE
        et_height_cm.text!!.clear()
        et_weight_kg.text!!.clear()

    }

    private fun setUssystem(){
        currentVisibleView = Us_View
        ll_bmi_calculator_layout.visibility = View.GONE // all depends on your xml layout,if using linerar layout us and metric on top
        // of each other then gone must be used, otherwise they will be on top of each other even if one is not visible and if using
        // relative layout and both linear layout of diff views are below same thing (here radio group) then invisible will work , although gone
        // can also work but as we are using relative layout other views whose position depend on one of the metric and us will be disturbed
        // if one is gone
        ll_bmi_calculator_layout_us.visibility = View.VISIBLE
        et_height_feet.text!!.clear()
        et_weight_lbs.text!!.clear()
        et_height_inch.text!!.clear()

    }

    private fun validateMetric() : Boolean{
        var isvalid = true
        if(et_height_cm.text.toString().isEmpty() || et_weight_kg.text.toString().isEmpty()|| et_weight_kg.text.toString()[0] == '0'
                || et_height_cm.text.toString()[0] =='0'){
            isvalid = false
        }
        return isvalid

    }
    private fun validateUsunits() : Boolean{
        var isvalid = true
        if(et_height_feet.text.toString().isEmpty() || et_weight_lbs.text.toString().isEmpty()|| et_weight_lbs.text.toString()[0] == '0'
                || et_height_feet.text.toString()[0] =='0'||et_height_inch.text.toString().isEmpty()){
            isvalid = false
        }
        return isvalid

    }

}