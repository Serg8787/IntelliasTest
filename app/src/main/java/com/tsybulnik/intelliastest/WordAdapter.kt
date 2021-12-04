package com.tsybulnik.intelliastest

import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class WordAdapter(val hashMap: Map<String?, String?>) :  RecyclerView.Adapter<WordAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var partOfSpech: TextView? = null
        var definion: TextView? = null

        init {
            partOfSpech = itemView.findViewById(R.id.partOfSpech)
            definion = itemView.findViewById(R.id.definion)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val keysFromMap:Array<String?> = hashMap.keys.toTypedArray()
        holder.partOfSpech?.text = keysFromMap[position]
        val keyFromMap:Array<String?> = hashMap.values.toTypedArray()
        holder.definion?.text = keyFromMap[position]
    }

    override fun getItemCount(): Int {
        return  hashMap.size
    }
}
