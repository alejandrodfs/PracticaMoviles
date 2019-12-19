package com.example.practicafinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        DBHelper receta = new DBHelper(this);
        SQLiteDatabase db = receta.getWritableDatabase();
        List<Receta> recetas = DBHelper.getRecetas(db);
        ArrayAdapter<Receta> adapter;

        final ListView milista = findViewById(R.id.milista);
        //String[] values = new String[]{"Ironman","Capitan America","Hulk","Thor","Black Widow","Ant man","Spider man","Capitan America","Hulk","Thor","Black Widow","Ant man","Spider man"};
        //va a haber que crear un adaptador para poder mostrar la lista bien
        adapter = new ArrayAdapter<Receta>(this, android.R.layout.simple_list_item_1,recetas);
        milista.setAdapter(adapter);


        milista.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                int item = position;
                Receta itemval = (Receta)milista.getItemAtPosition(position);
                String titulo = itemval.getTitulo();

                Toast.makeText(getApplicationContext(), "Position: "+ item+" – Valor: "+titulo, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, EditRegister.class);
                Bundle b = new Bundle();
                //pasamos el id para asi hacer una consulta en la base de datos y sacar todos los campos
                b.putString("RecetaID", itemval.getId());
                intent.putExtras(b);
                startActivity(intent);
            }

        });

    }

    public void clickBotonCrear( View v ){
        //Debemos pasar el nivel con el intent
        Intent intent = new Intent(MainActivity.this, CreateRegister.class);
        startActivity(intent);
    }

}
