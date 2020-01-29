package com.example.practicafinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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



    private Button buttonGuardarCambios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_register);
        //conseguimos el id del que habiamos seleccionado
        Bundle bundle = this.getIntent().getExtras();
        idreceta = bundle.getString("RecetaID");

        //procedemos a hacer la consulta
        final DBHelper dbHelper = new DBHelper(this);
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



        buttonGuardarCambios = findViewById(R.id.buttonGuardarCambios);
        buttonGuardarCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                //Recogemos los valores de titulo e ingredientes para comprobar que al menos no sean vacios estos campos de la receta
                String titulo = editTextTitulo.getText().toString();
                String ingredientes = editTextIngredientes.getText().toString();

                if(!titulo.isEmpty() && !ingredientes.isEmpty()){
                    //Una vez se cumpla esta recogemos los datos y creamos una receta, borramos la anterior y agregamos la nueva
                    Receta receta = new Receta (editTextTitulo.getText().toString(),editTextCategoria.getText().toString(),
                            editTextTiempo.getText().toString(),editTextCalorias.getText().toString(),
                            editTextIngredientes.getText().toString(),editTextDescripcion.getText().toString());

                    DBHelper.borrarReceta(db, idreceta);

                    DBHelper.crearReceta(db, receta);


                    Toast.makeText(getApplicationContext(), "Receta actualizada con exito", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(EditRegister.this, MainActivity.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(getApplicationContext(), "Los campos titulo e ingredientes deben estar rellenos", Toast.LENGTH_LONG).show();
                }







            }
        });
    }


}
