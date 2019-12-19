package com.example.practicafinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EditRegister extends AppCompatActivity {

    private SQLiteDatabase db = null;
    private Receta receta = null;
    private String idreceta;
    private EditText editTextTitulo;
    private EditText editTextCategoria;
    private EditText editTextTiempo;
    private EditText editTextCalorias;
    private EditText editTextIngredientes;
    private EditText editTextDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_register);
        //conseguimos el id del que habiamos seleccionado
        Bundle bundle = this.getIntent().getExtras();
        idreceta = bundle.getString("RecetaID");

        //procedemos a hacer la consulta
        DBHelper dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();

        try{
            receta = DBHelper.getUnaReceta(db, idreceta);
        }catch(Exception e){
            receta= null;
        }

        editTextTitulo = findViewById(R.id.titulo);
        editTextCategoria  = findViewById(R.id.categoria);
        editTextTiempo = findViewById(R.id.tiempo);
        editTextCalorias = findViewById(R.id.calorias);
        editTextIngredientes = findViewById(R.id.ingredientes);
        editTextDescripcion = findViewById(R.id.descripcion);

        editTextTitulo.setText(receta.getTitulo());
        editTextCategoria.setText(receta.getCategoria());
        editTextTiempo.setText(receta.getTiempo());
        editTextCalorias.setText(receta.getCalorias());
        editTextIngredientes.setText(receta.getIngredientes());
        editTextDescripcion.setText(receta.getDescripcion());


    }

    public void clickBotonBorrar (View v){

        DBHelper dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();
        DBHelper.borrarReceta(db, idreceta);
        Intent intent = new Intent(EditRegister.this, MainActivity.class);
        startActivity(intent);

    }
}
