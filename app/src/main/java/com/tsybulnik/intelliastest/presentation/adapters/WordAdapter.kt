package com.tsybulnik.intelliastest.presentation.adapters


import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.tsybulnik.intelliastest.databinding.ItemBinding

class WordAdapter(val hashMap: Map<String?, String?>) :  RecyclerView.Adapter<WordAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root){
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val keysFromMap:Array<String?> = hashMap.keys.toTypedArray()
        holder.binding.partOfSpech.text = keysFromMap[position]
        val keyFromMap:Array<String?> = hashMap.values.toTypedArray()
        holder.binding.definion.text = keyFromMap[position]
    }
    override fun getItemCount(): Int {
        return  hashMap.size
    }
}
