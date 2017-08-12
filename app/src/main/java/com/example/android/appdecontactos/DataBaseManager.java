package com.example.android.appdecontactos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Usuario on 11/08/2017.
 */

public class DataBaseManager {
    public static final String TABLE_NAME = "contactos";

    public static final String CN_ID = "_id";
    public static final String CN_NAME = "nombre";
    public static final String CN_PHONE = "telefono";

    public static final String CREATE_TABLE = "create table " +TABLE_NAME+ " ("
            + CN_ID + " integer primary key autoincrement, "
            + CN_NAME + " text not null, "
            + CN_PHONE + " text);";

    private DbHelper helper;
    private SQLiteDatabase db;

    public DataBaseManager(Context context) {

        helper = new DbHelper(context);
        db = helper.getWritableDatabase();


    }

    private ContentValues generarContentValues(String nombre, String telefono){
        ContentValues valores = new ContentValues();
        valores.put(CN_NAME, nombre);
        valores.put(CN_PHONE, telefono);

        return valores;

    }

    public void insertar(String nombre, String telefono){

        db.insert(TABLE_NAME, null, generarContentValues(nombre, telefono) );

    }

    public void insertar2(String nombre, String telefono){

        // notaCas. No está copiada la línea de abajo exactamente igual. En el vídeo no ponen
        // algunos espacios que yo sí que he puesto.
        db.execSQL("INSERT INTO " + TABLE_NAME + " values (null, '" + nombre + "', '" + telefono + "');");

    }

    public void eliminar (String nombre) {

        db.delete(TABLE_NAME, CN_NAME+"=?", new String[]{nombre});
    }

    public void eliminarMultiple (String nom1, String nom2){

        db.delete(TABLE_NAME, CN_NAME+"IN (?,?)", new String[]{nom1, nom2});
    }

    public void modificarTelefono(String nombre, String nuevoTelefono){

        db.update(TABLE_NAME, generarContentValues(nombre, nuevoTelefono), CN_NAME+"=?", new String[]{nombre});
    }

    public Cursor cargarCursorContactos(){

        String[] columnas = new String[]{CN_ID, CN_NAME, CN_PHONE};
        return db.query(TABLE_NAME, columnas, null, null, null, null,null);

    }

    public Cursor buscarContacto(String nombre){

        String[] columnas = new String[]{CN_ID, CN_NAME, CN_PHONE};
        return db.query(TABLE_NAME, columnas, CN_NAME + "=?", new String[]{nombre}, null, null, null);

    }



}









