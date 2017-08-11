package com.example.android.appdecontactos;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataBaseManager manager = new DataBaseManager(this);
        manager.insertar("Jaime", "666111222333");
        manager.insertar2("Pablo", "666222333444");





    }
}
