package com.example.wiltontuckerhart.proyectofinal.database;


import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = AppDataBase.NAME, version = AppDataBase.VERSION)
public class AppDataBase {

    public static final String NAME = "AppDatabase";
    public static final int VERSION = 1;
}