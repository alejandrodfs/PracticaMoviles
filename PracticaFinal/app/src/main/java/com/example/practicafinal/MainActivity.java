package com.example.practicafinal;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private Button buttonSearch;
    private EditText editTextBusqueda;
    private SQLiteDatabase db = null;
    private SQLiteDatabase db2 = null;
    private String busqueda ;

    ListView milista;
    List<Receta> recetas;
    ArrayAdapter<Receta> adapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(this);

       



        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.createReceta:
                startActivity(new Intent(this, CreateRegister.class));
                return true;

            default:
                return false;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTheme(R.style.AppTheme);
        try{
            Thread.sleep(5000);
        }catch(Exception e){

        }

        DBHelper receta = new DBHelper(this);
        SQLiteDatabase db = receta.getWritableDatabase();
        //Pasamos el parametro busqueda, si este es nulo devuelve todas las recetas sino hace la busqueda
        recetas = DBHelper.getRecetas(db);

        if(!recetas.isEmpty()){

            setContentView(R.layout.activity_receta_list);




            milista = findViewById(R.id.milista);

            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,recetas);
            milista.setAdapter(adapter);


            milista.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                    int item = position;
                    Receta itemval = (Receta)milista.getItemAtPosition(position);
                    String titulo = itemval.getTitulo();


                    Intent intent = new Intent(MainActivity.this, ShowRegister.class);
                    Bundle b = new Bundle();
                    //pasamos el id para asi hacer una consulta en la base de datos y sacar todos los campos
                    b.putString("RecetaID", itemval.getId());
                    intent.putExtras(b);
                    startActivity(intent);
                }

            });
        } else {
            setContentView(R.layout.activity_main);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        System.out.println("Entra");

        List<Receta> recetasBusqueda = new ArrayList<Receta>();

        if(!recetas.isEmpty()) {
            System.out.println("1");


            for (int i = 0; i < recetas.size(); i++) {

                if (query.equals(recetas.get(i).getTitulo())) {
                    recetasBusqueda.add(recetas.get(i));

                }
            }


            if (!recetasBusqueda.isEmpty()) {
                System.out.println("2");

                adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, recetasBusqueda);
                milista.setAdapter(adapter);

            } else {
                System.out.println("3");
                adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, recetas);
                milista.setAdapter(adapter);
                Toast.makeText(getApplicationContext(), "Sin resultados", Toast.LENGTH_LONG).show();
            }



        }else{
            System.out.println("4");
            Toast.makeText(getApplicationContext(), "No hay recetas", Toast.LENGTH_LONG).show();
        }


        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {


        return false;
    }
}


