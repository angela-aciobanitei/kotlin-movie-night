package com.ang.acb.movienight

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// Note: after creating the custom hilt app, need to update the manifest
// by adding the android:name=".MovieNightApplication" attribute inside
// the "application" tag, otherwise you get the following error: "Hilt
// Activity must be attached to an @HiltAndroidApp Application. Did you
// forget to specify your Application's class name in your manifest's
// <application />'s android:name attribute?"
@HiltAndroidApp
class MovieNightApplication : Application()