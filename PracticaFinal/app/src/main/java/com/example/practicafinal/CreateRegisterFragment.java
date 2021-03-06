package com.example.practicafinal;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;


import android.app.Fragment;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class CreateRegisterFragment extends Fragment implements View.OnClickListener, TextWatcher {


    private EditText titulo;
    private EditText categoria;
    private EditText tiempo;
    private EditText descripcion;
    private EditText calorias;
    private EditText ingredientes;
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler hdlr = new Handler();
    Button buttonGuardar;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_create_register, container, false);
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_create_register);


        titulo = (EditText)view.findViewById(R.id.titulo);
        categoria = (EditText)view.findViewById(R.id.categoria);
        tiempo = (EditText)view.findViewById(R.id.tiempo);
        descripcion = (EditText)view.findViewById(R.id.descripcion);
        calorias = (EditText)view.findViewById(R.id.calorias);
        ingredientes =(EditText)view. findViewById(R.id.ingredientes);

        buttonGuardar = (Button) view.findViewById(R.id.buttonGuardar);
        buttonGuardar.setOnClickListener(this);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);


        return view;

    }



    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onClick(View v) {
        String stringtitulo = titulo.getText().toString();
        String stringcategoria = categoria.getText().toString();
        String stringtiempo = tiempo.getText().toString();
        String stringdescripcion = descripcion.getText().toString();
        String stringcalorias = calorias.getText().toString();
        String stringingredientes = ingredientes.getText().toString();




        if(stringtitulo.isEmpty() || stringingredientes.isEmpty()){
            Toast.makeText(CreateRegisterFragment.this.getActivity(),"Es necesario rellanar el titulo y los ingredientes de la receta", Toast.LENGTH_LONG).show();
        }else{





            progressBar.setVisibility(View.VISIBLE);
            new PostTask().execute(stringtitulo, stringcategoria, stringtiempo, stringcalorias, stringingredientes, stringdescripcion);

            Intent intent = new Intent(CreateRegisterFragment.this.getActivity(), MainActivity.class);
            startActivity(intent);
        }



    }

    private final class PostTask extends AsyncTask<String, Void, String> {

        // Llamada al empezar
        @Override
        protected String doInBackground(String... params) {
            try {

                DBHelper recetaDB = new DBHelper(getActivity().getApplicationContext());
                SQLiteDatabase db = recetaDB.getWritableDatabase();
                Receta receta = new Receta(params[0], params[1], params[2], params[3], params[4], params[5]);
                DBHelper.crearReceta(db,receta);


            }catch(Exception e){

            }
            return null;
        }


        @Override
        protected void onPostExecute(String result) {

            super.onPostExecute(result);




            progressStatus = progressBar.getProgress();
            progressBar.setProgress(20);



                    while (progressStatus < 100) {
                        progressStatus += 1;
                        // Update the progress bar and display the current value in text view

                        progressBar.setProgress(progressStatus);

                        try {
                            // Sleep for 100 milliseconds to show the progress slowly.
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    //Toast.makeText(CreateRegisterFragment.this.getActivity(), "Receta creada correctamente", Toast.LENGTH_LONG).show();
                }




        }







}
