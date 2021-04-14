package com.example.a7minuteworkout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.history_rv_setup.view.*

class HistoryRViewAdapter( val items : ArrayList<String>,val context : Context):
            RecyclerView.Adapter<HistoryRViewAdapter.ViewHolder>(){

                class ViewHolder(view:View) : RecyclerView.ViewHolder(view){
                        val tv_index = view.tv_rv_index
                        val tv_date = view.tv_rv_date
                        val ll_rv = view.ll_rv
                }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.history_rv_setup,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val text = items.get(position)
        val index = position +1
        holder.tv_index.setText(index.toString())
        holder.tv_date.setText(text)
        if(position%2 == 0){
            holder.ll_rv.setBackgroundColor(ContextCompat.getColor(context,R.color.off_white))
        }else{
            holder.ll_rv.setBackgroundColor(ContextCompat.getColor(context,R.color.white))
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}