package com.nepoapp.nepoplay20.core.repositories

import android.content.Context
import android.database.Cursor
import android.os.Bundle
import android.provider.MediaStore
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
/**
 * Created in 27/05/2020 as 19:26
 *   by Lucas Nepomuceno Santos
 *
 * **/
class CursorRepository() : LoaderManager.LoaderCallbacks<Cursor>{

    private lateinit var context : Context
    private lateinit var cursorModelCallback : (data: Cursor?) -> Unit

    constructor(context : Context, cursorModelCallback : (data: Cursor?) -> Unit) : this(){
        this@CursorRepository.context = context
        this@CursorRepository.cursorModelCallback = cursorModelCallback
    }

    fun getContext() = this

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> =
        CursorLoader(
            context,
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            arrayOf(
                MediaStore.MediaColumns.DISPLAY_NAME,
                MediaStore.MediaColumns.DATA,
                MediaStore.MediaColumns._ID
            ),
            "${MediaStore.Audio.AudioColumns.IS_MUSIC} = 1",
            null,
            null
        )

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        cursorModelCallback.invoke(data)
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        cursorModelCallback.invoke(null)
    }

}