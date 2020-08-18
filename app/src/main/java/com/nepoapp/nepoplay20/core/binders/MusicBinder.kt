package com.nepoapp.nepoplay20.core.binders

import android.os.Binder
import com.nepoapp.nepoplay20.core.listeners.MusicListenerService

class MusicBinder(val musicListerService : MusicListenerService) : Binder()