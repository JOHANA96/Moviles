package com.example.moviles.fragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviles.R;
import com.example.moviles.RegistrarTiendaActivity;
import com.example.moviles.TiendaAdapter;
import com.example.moviles.modelo.Tienda;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class TiendaFragment extends Fragment {
    RecyclerView contenedorTienda;
    private static final String TAG = "Tienda Fragment";
    FloatingActionButton registrarTienda;
    TiendaAdapter tiendaAdapter;

    public TiendaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_tienda, container, false);
        contenedorTienda = view.findViewById(R.id.contenedortienda);
        LinearLayoutManager linearLayout = new LinearLayoutManager(getContext());
        linearLayout.setOrientation(LinearLayoutManager.VERTICAL);
        contenedorTienda.setLayoutManager(linearLayout);
        tiendaAdapter = new TiendaAdapter(getActivity(),cargarDatosFirebase(), R.id.card);
        contenedorTienda.setAdapter(tiendaAdapter);
        registrarTienda = view.findViewById(R.id.registrarTienda);
        registrarTienda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irRegistarTienda();
            }
        });
        return view;
    }

    private void irRegistarTienda() {
        Intent intent = new Intent(getActivity(), RegistrarTiendaActivity.class);
        startActivity(intent);
    }

   /* public ArrayList<Tienda> cargarDatos(){
        ArrayList<Tienda> tiendas = new ArrayList<>();
        tiendas.add(new Tienda());
        return tiendas;
    }*/

//Consulta de firebase
    public ArrayList<Tienda> cargarDatosFirebase(){
       final ArrayList<Tienda> tiendas = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef.child("tiendas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

               if(dataSnapshot.exists()){
                   tiendas.clear();
                   for (DataSnapshot data : dataSnapshot.getChildren()) {
                       Tienda tienda = data.getValue(Tienda.class);
                       tiendas.add(tienda);
                       tiendaAdapter.notifyDataSetChanged();
                   }
               }
                //Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value Johana.", error.toException());
            }
        });
        return tiendas;

    }


}

