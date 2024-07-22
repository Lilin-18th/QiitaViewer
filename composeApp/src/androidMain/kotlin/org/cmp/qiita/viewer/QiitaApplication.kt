package org.cmp.qiita.viewer

import android.app.Application
import koin.initKoin
import org.koin.android.ext.koin.androidContext

class QiitaApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@QiitaApplication)
        }
    }
}