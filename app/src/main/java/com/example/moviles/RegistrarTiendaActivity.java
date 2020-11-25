package com.example.moviles;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.moviles.modelo.Tienda;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrarTiendaActivity extends AppCompatActivity {
     TextInputEditText imagen, nombre, descripcion, horario, telefono;
     Button guardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_tienda);
        imagen = findViewById(R.id.txtimagen);
        nombre = findViewById(R.id.txtnombre);
        descripcion = findViewById(R.id.txtdescripcionnuevatienda);
        horario = findViewById(R.id.txthorarionuevatienda);
        telefono = findViewById(R.id.txttelefonotiendanueva);
        guardar = findViewById(R.id.btguardar);

                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        guardarTienda();
                    }
                });

    }

    private void guardarTienda() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        Tienda tienda = new Tienda(myRef.push().getKey(), imagen.getText().toString(), nombre.getText().toString(), descripcion.getText().toString(), horario.getText().toString(), null, 0, 0,  telefono.getText().toString());
        myRef.child("tiendas").child(tienda.getId()).setValue(tienda);
        finish();
    }
}