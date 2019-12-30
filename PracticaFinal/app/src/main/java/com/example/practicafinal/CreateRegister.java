package com.example.practicafinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class CreateRegister extends AppCompatActivity {

    private EditText titulo;
    private EditText categoria;
    private EditText tiempo;
    private EditText descripcion;
    private EditText calorias;
    private EditText ingredientes;
    private ProgressBar progressBar;

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
        progressBar = findViewById(R.id.progressBar);

    }
    public void onClickGuardar (View v){

        String stringtitulo = titulo.getText().toString();
        String stringcategoria = categoria.getText().toString();
        String stringtiempo = tiempo.getText().toString();
        String stringdescripcion = descripcion.getText().toString();
        String stringcalorias = calorias.getText().toString();
        String stringingredientes = ingredientes.getText().toString();




        if(stringtitulo.isEmpty() || stringingredientes.isEmpty()){
            Toast.makeText(CreateRegister.this,"Es necesario rellanar el titulo y los ingredientes de la receta",Toast.LENGTH_LONG).show();
        }else{



            progressBar.setVisibility(View.VISIBLE);

            new PostTask().execute(stringtitulo, stringcategoria, stringtiempo, stringcalorias, stringingredientes, stringdescripcion);

            Intent intent = new Intent(CreateRegister.this, MainActivity.class);
            startActivity(intent);
        }



    }

    private final class PostTask extends AsyncTask<String, Void, String> {

        // Llamada al empezar
        @Override
        protected String doInBackground(String... params) {
            try {

                DBHelper recetaDB = new DBHelper(getApplicationContext());
                SQLiteDatabase db = recetaDB.getWritableDatabase();
                Receta receta = new Receta(params[0], params[1], params[2], params[3], params[4], params[5]);
                DBHelper.crearReceta(db,receta);

            }catch(Exception e){

            }
            return null;
        }


        @Override
        protected void onPostExecute(String result) {


            progressBar.setVisibility(View.GONE);

            Toast.makeText(CreateRegister.this, "Receta creada correcetamente", Toast.LENGTH_LONG).show();

        }
    }
}
