package com.example.wiltontuckerhart.proyectofinal.database.models;


import com.example.wiltontuckerhart.proyectofinal.database.AppDataBase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = AppDataBase.class)
public class Users extends BaseModel {

    @Column
    @PrimaryKey(autoincrement = true)
    public long id;

    @Column
    public String username;

    @Column
    public String password;

    @Column
    public String nombre;

    @Column
    public String roll;

}
