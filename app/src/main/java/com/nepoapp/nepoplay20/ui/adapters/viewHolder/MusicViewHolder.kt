package com.nepoapp.nepoplay20.ui.adapters.viewHolder

import android.content.Context
import android.graphics.Color
import android.view.ViewGroup
import androidx.core.view.marginTop
import androidx.recyclerview.widget.RecyclerView
import com.nepoapp.nepoplay20.R
import com.nepoapp.nepoplay20.databinding.ItemDataMusicBinding
import java.util.concurrent.CopyOnWriteArrayList

class MusicViewHolder : RecyclerView.ViewHolder{
    private var view : ItemDataMusicBinding

    constructor(view : ItemDataMusicBinding,context: Context) : super(view.root){
        this.view = view
        patternDesignCard(context,view)
    }

    fun bindView(nameMusic : String){
        view.nameMusic.text = nameMusic
    }

    private fun patternDesignCard(context: Context,view : ItemDataMusicBinding){
        when((0..4).random()){
            0 -> setPatternZero(context,view)
            1 -> setPatternTwo(context,view)
            2 -> setPatterThree(context,view)
            3 -> setPatterFor(context,view)
        }
    }

    private fun setPatternZero(context: Context, view: ItemDataMusicBinding) {
        view.apply {
            rootBackground.background = context.getDrawable(R.drawable.ui_background_0)
            nameMusic.setTextColor(Color.WHITE)
        }
    }

    private fun setPatternTwo(context: Context, view: ItemDataMusicBinding){
        view.apply {
            rootBackground.background = context.getDrawable(R.drawable.ui_background_1)
            nameMusic.setTextColor(Color.BLACK)
        }
    }

    private fun setPatterThree(context: Context, view: ItemDataMusicBinding){
        view.apply {
            rootBackground.background = context.getDrawable(R.drawable.ui_background_2)
            nameMusic.setTextColor(Color.GRAY)
        }
    }

    private fun setPatterFor(context: Context, view: ItemDataMusicBinding){
        view.apply {
            rootBackground.background = context.getDrawable(R.drawable.ui_background_3)
            nameMusic.setTextColor(Color.WHITE)
        }
    }

}