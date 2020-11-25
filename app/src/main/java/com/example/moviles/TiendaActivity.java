package com.example.moviles;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class TiendaActivity extends AppCompatActivity {

     TabItem tienda,perfil,mapa;
     TabLayout menu;
     ViewPager contenedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda);
        tienda = findViewById(R.id.tienda);
        menu = findViewById(R.id.menu);
        contenedor = findViewById(R.id.contenedor);
        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager(), menu.getTabCount());
        contenedor.setAdapter(pageAdapter);
        contenedor.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(menu));
    }
}