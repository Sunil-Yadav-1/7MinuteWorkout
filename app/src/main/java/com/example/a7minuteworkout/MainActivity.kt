package com.example.a7minuteworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ll_start.setOnClickListener{
           val intent = Intent(this,ExerciseActivity::class.java)
            startActivity(intent)
        }
        ll_BMI.setOnClickListener{
            val intent = Intent(this,bmi_calculator::class.java)
            startActivity(intent)
        }
        ll_history.setOnClickListener{
            val intent = Intent(this, HistoryActivity :: class.java)
            startActivity(intent)

        }
    }

}