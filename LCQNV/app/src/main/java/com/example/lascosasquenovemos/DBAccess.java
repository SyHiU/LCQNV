package com.example.lascosasquenovemos;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DBAccess {
    private DatabaseReference mDatabase;
    private Context appContext;

    public DBAccess(){

    }

    public DBAccess(Context contexto){
        appContext = contexto;
        FirebaseDatabase mDatabaseInstance = FirebaseDatabase.getInstance(contexto.getString(R.string.firebase_realtime_database_URL));
        mDatabaseInstance.setPersistenceEnabled(true);
        mDatabase = mDatabaseInstance.getReference();
    }

    public boolean comprobarFormato(String nombre, String descripcion){
        if (nombre.equals("") || descripcion.equals("") || nombre.length() > 50 || descripcion.length() > 200) return false;
        else return true;
    }


    public int escribirBD (String nombre, String desc){
        if(comprobarFormato(nombre, desc)){
           mDatabase.child(String.valueOf(nombre)).setValue(String.valueOf(desc)).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("firebase", e.getLocalizedMessage());
                }
            });
            return 0;
        } else{
            return -1;
        }
    }
}
