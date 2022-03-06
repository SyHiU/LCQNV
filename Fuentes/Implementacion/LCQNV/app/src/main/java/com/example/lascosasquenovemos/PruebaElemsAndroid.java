package com.example.lascosasquenovemos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PruebaElemsAndroid extends AppCompatActivity {

    CheckBox c1, c2;
    TextView t1;
    Button btn, btn2, btn3;
    ImageView img;
    Intent plbd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba_elems_android);

        //Referenciamos los elementos de la view que vamos a usar en la Activity
        c1 =  findViewById(R.id.checkBox1);
        c2 =  findViewById(R.id.checkBox2);
        t1 = findViewById(R.id.Texto);
        btn = findViewById(R.id.buttonValidar);
        btn2 = findViewById(R.id.buttonImagen);
        btn3 = findViewById(R.id.buttonPasarPantalla);
        img = findViewById(R.id.Imagen);

        img.setVisibility(View.INVISIBLE); //Hago que la imagen esté en invisible por defecto

        inicilizar();
    }

    private void inicilizar() { //Creo los botones y le doy su funcionalidad al clickar en ellos

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                validar();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                imagenFunciones();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                pasarPantalla();
            }
        });


    }

    private void pasarPantalla() {
        plbd = new Intent(PruebaElemsAndroid.this, PruebaEscribirBD.class);
        startActivity(plbd);
    }


    private void validar(){

        String aux = "No se ha seleccionado ningún checkbox";
        t1.setText(aux);

        if(c1.isChecked()){
            t1.setText("Se ha seleccionado el checkbox uno");
        }
        if(c2.isChecked()){
            t1.setText("Se ha seleccionado el checkbox dos");
        }

        if(c1.isChecked() && c2.isChecked()){
            t1.setText("Se han seleccionado los dos checkbox");
        }
    }

    private void imagenFunciones(){

        if(img.isShown()){
            img.setVisibility(View.INVISIBLE);
        } else{
            img.setVisibility(View.VISIBLE);
        }

    }
}