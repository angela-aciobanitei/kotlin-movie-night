package com.ang.acb.movienight.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// Note: beware of this error "Hilt Activity must be attached to an @HiltAndroidApp
// Application. Did you forget to specify your Application's class name in your manifest's
// <application />'s android:name attribute?"
@HiltAndroidApp
class MovieNightApplication : Application()