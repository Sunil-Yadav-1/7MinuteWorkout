package com.example.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_finish.*
import java.text.SimpleDateFormat
import java.util.*

class finish : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)
        setSupportActionBar(toolbar_finish_activity)
        val actionbar = supportActionBar
        if(actionbar != null){
            actionbar.setDisplayHomeAsUpEnabled(true)
        }
        toolbar_finish_activity.setNavigationOnClickListener {
           finish()
            onBackPressed()
        }

        finish_button.setOnClickListener{
            finish()
        }
        addDatetoDatabase()
    }
    private fun addDatetoDatabase(){
        var calendar = Calendar.getInstance()
        val datetime = calendar.time
        Log.i("DATE",""+datetime)
        var sdf = SimpleDateFormat("dd MMM yyyy HH:mm:ss",Locale.getDefault())
        val date = sdf.format(datetime)
        val dbase = SQliteHelper(this,null)
        dbase.addDate(date)
        Log.i("DATE : ","Added")
    }



}