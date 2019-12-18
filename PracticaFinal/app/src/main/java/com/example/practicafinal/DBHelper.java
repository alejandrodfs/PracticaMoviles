package com.example.practicafinal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBHelper extends SQLiteOpenHelper {

        private static final String TAG = DBHelper.class.getSimpleName();
        // Constructor
        public DBHelper(Context context) {
            super(context, RecetaContract.DB_NAME, null, RecetaContract.DB_VERSION);
        }
        // Llamado para crear la tabla
        @Override
        public void onCreate(SQLiteDatabase db) {
            String sql = String.format("create table %s (%s text primary key, %s text, %s text, %s text, %s text, %s text, %s text)",
            RecetaContract.TABLE,
                    RecetaContract.Column.ID,
                    RecetaContract.Column.titulo,
                    RecetaContract.Column.categoria,
                    RecetaContract.Column.tiempo,
                    RecetaContract.Column.calorias,
                    RecetaContract.Column.ingredientes,
                    RecetaContract.Column.descripcion);
            Log.d(TAG,
                    "onCreate con SQL: " + sql);
            db.execSQL(sql);
        }
        // Llamado siempre que tengamos una nueva version
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Aqui irían las sentencias del tipo ALTER TABLE, de momento lo hacemos mas sencillo...
// Borramos la vieja base de datos
            db.execSQL("drop table if exists " + RecetaContract.TABLE);
// Creamos una base de datos nueva
            onCreate(db);
            Log.d(TAG, "onUpgrade");
        }
        public static void crearReceta (SQLiteDatabase db, Receta receta){

            String sql = "INSERT INTO receta (_id, titulo, categoria, tiempo, calorias, ingredientes, descripcion)  VALUES"+ "('"+receta.getId()+"','"+receta.getTitulo()+"','"+receta.getCategoria()+"','"+receta.getTiempo()+"','"+receta.getCalorias()+"','"+receta.getIngredientes()+"','"+receta.getDescripcion()+"')";
            db.execSQL(sql);
        }
    }

