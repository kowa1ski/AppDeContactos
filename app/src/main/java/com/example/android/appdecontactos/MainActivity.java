package com.example.android.appdecontactos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DataBaseManager manager;
    private Cursor cursor;
    private ListView lista;
    private SimpleCursorAdapter adapter;
    private TextView textView;
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = new DataBaseManager(this);
        lista = (ListView) findViewById(R.id.list);

        textView = (TextView) findViewById(R.id.editText);
        imageButton = (ImageButton) findViewById(R.id.imageButton);

        imageButton.setOnClickListener(this);

        manager.insertar("Jaime", "666111222333");
        manager.insertar2("Pablo", "666222333444");

        /**
        manager.insertar("Jaime", "666111222333");
        manager.insertar2("Pablo", "666222333444");
         */

        String[] from = new String[]{manager.CN_NAME, manager.CN_PHONE};
        int[] to = new int[]{android.R.id.text1, android.R.id.text2 };

        cursor = manager.cargarCursorContactos();
        adapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item, cursor, from, to, 0);
        lista.setAdapter(adapter);







    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imageButton){

            Cursor c = manager.buscarContacto(textView.getText().toString());
            adapter.changeCursor(c);

        }
    }
}
