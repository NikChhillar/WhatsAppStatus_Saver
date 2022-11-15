package com.example.whatsappstatussaver

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.status_item.view.*

class StatusAdapter(private val context: Context,
                    private var modelClass:ArrayList<ModelClass>,
                    private val clickListener:(ModelClass)->Unit) :
    RecyclerView.Adapter<StatusAdapter.StatusViewHolder>() {

    class StatusViewHolder(itemView:View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatusViewHolder {
        return StatusViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.status_item,parent,false)
        )
    }

    override fun onBindViewHolder(holder: StatusViewHolder, position: Int) {
        if (modelClass[position].fileUri.endsWith(".mp4")){
            holder.itemView.cvVideoIcon.visibility= View.VISIBLE
            holder.itemView.ivVideoIcon.visibility= View.VISIBLE
        }else{
            holder.itemView.cvVideoIcon.visibility= View.GONE
            holder.itemView.ivVideoIcon.visibility= View.GONE
        }

        Glide.with(context).load(Uri.parse(modelClass[position].fileUri)).into(holder.itemView.ivStatus)

        holder.itemView.ivStatus.setOnClickListener {
            clickListener(modelClass[position])
        }
    }

    override fun getItemCount(): Int {
        return modelClass.size
    }
}