package com.fitness;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by yani on 31.8.2016 г..
 */

public class FitnessApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
