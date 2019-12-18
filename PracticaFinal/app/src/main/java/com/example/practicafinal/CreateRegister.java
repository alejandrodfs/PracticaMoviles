package com.example.practicafinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateRegister extends AppCompatActivity {

    EditText titulo;
    EditText categoria;
    EditText tiempo;
    EditText descripcion;
    EditText calorias;
    EditText ingredientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_register);


        titulo = findViewById(R.id.titulo);
        categoria = findViewById(R.id.categoria);
        tiempo = findViewById(R.id.tiempo);
        descripcion = findViewById(R.id.descripcion);
        calorias = findViewById(R.id.calorias);
        ingredientes = findViewById(R.id.ingredientes);

    }
    public void onClickGuardar (View v){

        String stringtitulo = titulo.getText().toString();
        String stringcategoria = categoria.getText().toString();
        String stringtiempo = tiempo.getText().toString();
        String stringdescripcion = descripcion.getText().toString();
        String stringcalorias = calorias.getText().toString();
        String stringingredientes = ingredientes.getText().toString();


        DBHelper recetaDB = new DBHelper(this);
        SQLiteDatabase db = recetaDB.getWritableDatabase();

        if(stringtitulo.isEmpty() || stringingredientes.isEmpty()){
            Toast.makeText(CreateRegister.this,"Es necesario rellanar el titulo y los ingredientes de la receta",Toast.LENGTH_LONG).show();
        }else{

            Receta receta = new Receta(stringtitulo, stringcategoria, stringtiempo, stringcalorias, stringingredientes, stringdescripcion );
            DBHelper.crearReceta(db,receta);
            Intent intent = new Intent(CreateRegister.this, MainActivity.class);
            startActivity(intent);
        }



    }
}
