package com.nepoapp.nepoplay20.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nepoapp.nepoplay20.databinding.ItemDataMusicBinding
import com.nepoapp.nepoplay20.ui.adapters.viewHolder.MusicViewHolder

class MusicAdapter(val context : Context)
    : RecyclerView.Adapter<MusicViewHolder>() {

    val list = arrayListOf<String>("Nome de teste 1 dasdasdasdasdasda","Nome de teste 2 sadas")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder =
        MusicViewHolder(ItemDataMusicBinding.inflate(LayoutInflater.from(parent.context)),context)

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.bindView(list[position])
    }
}