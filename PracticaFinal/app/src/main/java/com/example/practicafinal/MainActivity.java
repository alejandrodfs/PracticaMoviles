package com.example.practicafinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView milista = findViewById(R.id.milista);
        String[] values = new String[]{"Ironman","Capitan America","Hulk","Thor","Black Widow","Ant man","Spider man","Capitan America","Hulk","Thor","Black Widow","Ant man","Spider man"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);
        milista.setAdapter(adapter);
        milista.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                int item = position;
                String itemval = (String)milista.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "Position: "+ item+" – Valor: "+itemval, Toast.LENGTH_LONG).show();
            }

        });

    }

    public void clickBotonCrear( View v ){
        //Debemos pasar el nivel con el intent
        Intent intent = new Intent(MainActivity.this, CreateRegister.class);
        startActivity(intent);
    }

}
