package com.example.lascosasquenovemos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PruebaLeerBD extends AppCompatActivity {

    private DatabaseReference mDatabase;
    EditText id;
    String desc;
    TextView desc_view;
    Button btn, btn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba_leer_bd);

        FirebaseDatabase mDatabaseInstance = FirebaseDatabase.getInstance(getString(R.string.firebase_realtime_database_URL));
        mDatabaseInstance.setPersistenceEnabled(true);
        mDatabase = mDatabaseInstance.getReference();

        btn = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        desc_view = findViewById(R.id.lbldescIn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = (EditText) findViewById(R.id.txtBasico);
                desc = id.getText().toString();
                mDatabase.child(desc).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (!task.isSuccessful()) {
                            Log.e("firebase", "Error getting data", task.getException());
                            desc_view.setText("ID no encontrado");
                        }
                        else {
                            Log.d("firebase", String.valueOf(task.getResult().getValue()));

                            if(String.valueOf(task.getResult().getValue()) == "null"){
                                desc_view.setText("ID no encontrado");
                            }
                            else{
                                desc_view.setText(String.valueOf(task.getResult().getValue()));
                            }

                        }
                    }
                });
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PruebaLeerBD.this, PruebaElemsAndroid.class));
            }
        });







    }
}