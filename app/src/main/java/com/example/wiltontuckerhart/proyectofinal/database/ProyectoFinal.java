package com.example.wiltontuckerhart.proyectofinal.database;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

public class ProyectoFinal extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
    }
}