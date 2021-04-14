package com.example.a7minuteworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        setSupportActionBar(toolbar_history_activity)
        val actionbar = supportActionBar
          if(actionbar != null){
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.title = "History"
        }

        toolbar_history_activity.setNavigationOnClickListener {
            onBackPressed()
        }
        setupRecyclerView()

    }
    private fun setupRecyclerView(){
        val dbhandler = SQliteHelper(this,null)
        val list = dbhandler.getDate()
        if(list.isNotEmpty()){
            rv_history_exercises.visibility = View.VISIBLE
            tv_no_record.visibility = View.GONE
            rv_history_exercises.layoutManager = LinearLayoutManager(this)
            val historyAdapter = HistoryRViewAdapter(list,this)
            rv_history_exercises.adapter = historyAdapter

        }else{
            rv_history_exercises.visibility = View.GONE
            tv_no_record.visibility = View.VISIBLE
        }
    }
}