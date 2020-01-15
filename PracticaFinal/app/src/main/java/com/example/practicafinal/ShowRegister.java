package com.example.practicafinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ShowRegister extends AppCompatActivity {

    private SQLiteDatabase db = null;
    private Receta receta = null;
    private String idreceta;
    private TextView textViewTitulo;
    private TextView textViewCategoria;
    private TextView textViewTiempo;
    private TextView textViewCalorias;
    private TextView textViewIngredientes;
    private TextView textViewDescripcion;
    private Button buttonEditar;
    private Button buttonDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_register);

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

        textViewTitulo = findViewById(R.id.Nombretitulo);
        textViewCategoria  = findViewById(R.id.Nombrecategoria);
        textViewTiempo = findViewById(R.id.Nombretiempo);
        textViewCalorias = findViewById(R.id.Nombrecalorias);
        textViewIngredientes = findViewById(R.id.Nombreingredientes);
        textViewDescripcion = findViewById(R.id.Nombredescripcion);

        buttonEditar = findViewById(R.id.buttonEditar);
        buttonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ShowRegister.this, EditRegister.class);
                Bundle b = new Bundle();
                //pasamos el id para asi hacer una consulta en la base de datos y sacar todos los campos
                b.putString("RecetaID", receta.getId());
                intent.putExtras(b);
                startActivity(intent);

            }
        });

        buttonDelete = findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db = dbHelper.getWritableDatabase();
                DBHelper.borrarReceta(db, receta.getId());
                Toast.makeText(getApplicationContext(), "Has eliminado la receta correctamente", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ShowRegister.this, MainActivity.class);
                startActivity(intent);

            }
        });


        textViewTitulo.setText(receta.getTitulo());
        textViewCategoria.setText(receta.getCategoria());
        textViewTiempo.setText(receta.getTiempo());
        textViewCalorias.setText(receta.getCalorias());
        textViewIngredientes.setText(receta.getIngredientes());
        textViewDescripcion.setText(receta.getDescripcion());
    }


}
