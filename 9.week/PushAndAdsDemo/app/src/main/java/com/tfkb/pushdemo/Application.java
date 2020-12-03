package com.tfkb.pushdemo;

import android.app.Application;

 class App  extends Application {
     @Override
     public void onCreate() {
         super.onCreate();
         LocalStorage.init(this);
     }
 }
