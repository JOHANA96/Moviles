package com.example.moviles;

import com.example.moviles.fragmentos.MapaFragment;
import com.example.moviles.fragmentos.PerfilFragment;
import com.example.moviles.fragmentos.TiendaFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter {
    private int numItems;
    public PageAdapter(@NonNull FragmentManager fm, int numItems) {
        super(fm);
        this.numItems=numItems;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new TiendaFragment();
            case 1:
                return new PerfilFragment();
            case 2:
                return new MapaFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numItems;
    }
}
