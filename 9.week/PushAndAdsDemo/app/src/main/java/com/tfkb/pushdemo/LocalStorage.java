package com.tfkb.pushdemo;

import android.content.Context;
import android.content.SharedPreferences;

public class LocalStorage {
    private static SharedPreferences preferences;
    private static int appLaunch;
    private static String APP_LUNCH = "APP_LUNCH";

    public static  void init(Context context){
        preferences =  context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }

    public static void increaseLunch(){
        appLaunch = appLaunch + 1;
        preferences.edit().putInt(APP_LUNCH, appLaunch);
    }

    private static int getAppLaunch(){
        return preferences.getInt(APP_LUNCH, 0);
    }

    private static void resetAppLunch(){
        appLaunch = 0;
        preferences.edit().putInt(APP_LUNCH, appLaunch);

    }

    public static Boolean isAvailableForInters(){
        if (getAppLaunch() >= 3){
            resetAppLunch();
            return true;
        }

        else return false;
    }
}
