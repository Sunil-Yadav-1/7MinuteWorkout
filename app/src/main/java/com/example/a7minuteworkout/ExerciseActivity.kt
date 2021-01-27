package com.example.a7minuteworkout

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_exercise.*
import kotlinx.android.synthetic.main.custom_dialog_file.*
import java.lang.Exception
import java.util.*

class ExerciseActivity : AppCompatActivity() , TextToSpeech.OnInitListener{
    private var resttimr : CountDownTimer? = null
    private var exercisetimr : CountDownTimer? = null
    private val exercise_time : Long = 30000
    private var tp_exercise = 0
    private var timepassed  = 0
    private val totalTime : Long = 10000
    private var exerciselist = Exercises_Constant.getExerciseList()
    private var exerciseNumber = -1
    private var tts : TextToSpeech? = null
    private var player : MediaPlayer? = null
    private var exerciseAdapter : ExerciseStatusAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        setSupportActionBar(toolbar_exercise_activity)
        val actionbar = supportActionBar
        if(actionbar != null){
            actionbar.setDisplayHomeAsUpEnabled(true)
        }
        toolbar_exercise_activity.setNavigationOnClickListener {
            customDialog()
        }
        tts = TextToSpeech(this,this)
        setUpTimr()
        setupExerciseStatusRecyclerView()

    }

    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS){
            var result = tts!!.setLanguage(Locale.ENGLISH)
            if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("tts","Not Supported")
            }
        }else{
            Log.e("tts","Initialization error")

        }
    }

    private fun setProgressBar(){

        resttimr = object : CountDownTimer(totalTime,1000){
            override fun onTick(millisUntilFinished: Long) {
                timepassed++
                progressbar.progress =(totalTime/1000).toInt() -   timepassed
                timertv.text = ((totalTime/1000).toInt() -  timepassed).toString()
            }

            override fun onFinish() {
                exerciseNumber++
                exerciselist[exerciseNumber].setisSelected(true)
                exerciseAdapter!!.notifyDataSetChanged()
                setUpTimr_exercise()
            }
        }
        (resttimr as CountDownTimer).start()
    }
    private fun setProgressBar_exercise(){

        exercisetimr = object : CountDownTimer(exercise_time,1000){
            override fun onTick(millisUntilFinished: Long) {
                tp_exercise++
                progressbar_second.progress =(exercise_time/1000).toInt() -   tp_exercise
                timertv_second.text = ((exercise_time/1000).toInt() -  tp_exercise).toString()
            }

            override fun onFinish() {
                //if(exerciseNumber>=exerciselist.size -1)
                if(exerciseNumber>1){
                    exerciselist[exerciseNumber].setisSelected(false)
                    exerciselist[exerciseNumber].setisCompleted(true)
                    exerciseAdapter!!.notifyDataSetChanged()
                    Toast.makeText(this@ExerciseActivity,"Workout Complete",Toast.LENGTH_SHORT).show()
                    finish()
                    val intent = Intent(this@ExerciseActivity,finish::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this@ExerciseActivity,"Over to next rest screen",Toast.LENGTH_SHORT).show()
                    setUpTimr()
                    exerciselist[exerciseNumber].setisSelected(false)
                    exerciselist[exerciseNumber].setisCompleted(true)
                    exerciseAdapter!!.notifyDataSetChanged()
                }

            }
        }
        (exercisetimr as CountDownTimer).start()
    }
    override fun onDestroy() {
        if(resttimr!= null){
            resttimr!!.cancel()
            resttimr = null
        }


            if(tts!=null){
                tts!!.stop()
                tts!!.shutdown()
                tts = null}
        if(exercisetimr!= null){
            exercisetimr!!.cancel()
            exercisetimr = null
        }
        if(player!= null){
            player!!.stop()
        }
        super.onDestroy()
    }

    private fun setUpTimr(){
        if(resttimr!= null){
            resttimr!!.cancel()
            resttimr = null
        }
        try {
          //  val startUri =  Uri.parse("android : resource :// com.example.a7minuteworkout/" + R.raw.sound)
            player = MediaPlayer.create(applicationContext, R.raw.sound)
            player!!.isLooping = false
            player!!.start()
        }catch (e: Exception){
            e.printStackTrace()
        }
        tv_next_exercise.text = exerciselist[exerciseNumber+1].getname()
        ll_rest.visibility = View.VISIBLE
        ll_rest_second.visibility = View.GONE
        timepassed = 0
        setProgressBar()
    }
    private fun setUpTimr_exercise(){
        ll_rest.visibility = View.GONE
        ll_rest_second.visibility = View.VISIBLE

        val ex_name = exerciselist[exerciseNumber].getname()
        tv_exercise_name.text = ex_name
        image_exercise.setImageResource(exerciselist[exerciseNumber].getimage())

        if(exercisetimr!= null){
            exercisetimr!!.cancel()
            exercisetimr = null
        }
        tp_exercise = 0
        speakOut(ex_name)
        setProgressBar_exercise()
    }

    private fun speakOut(str : String){
        if(tts != null){
            tts!!.speak(str,TextToSpeech.QUEUE_FLUSH,null,"")
        }else{

        }
    }

    private fun setupExerciseStatusRecyclerView(){
        rvExerciseStatus.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        exerciseAdapter = ExerciseStatusAdapter(exerciselist!!,this)
        rvExerciseStatus.adapter = exerciseAdapter

    }
    private fun customDialog(){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.custom_dialog_file)
        dialog.tv_yes.setOnClickListener({
            finish()
            dialog.dismiss()
        })
        dialog.tv_no.setOnClickListener({
            dialog.dismiss()
        }
        )
        dialog.show()
        dialog.setCancelable(false)
    }
}