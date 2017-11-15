package com.huutrung.sachcuatui;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.huutrung.sachcuatui.Book.MyFragment1;
import com.huutrung.sachcuatui.NhatKi.AllNhatKiFragment;
import com.huutrung.sachcuatui.NhatKi.NhatKiHomeFragment;

import java.util.ArrayList;

public class NhatKiActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawerLayoutNhatKiHome;
    ListView navListNhatKiHome;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhat_ki);

        drawerLayoutNhatKiHome = (DrawerLayout) findViewById(R.id.drawerLayoutNhatKiHome);
        navListNhatKiHome = (ListView) findViewById(R.id.navListNhatKiHome);

        //khoi tao cac thuoc tinh cos trong thanh navigation

        ArrayList<String> navArrNhatKi = new ArrayList<String>();
        navArrNhatKi.add("Write Story");
        navArrNhatKi.add("All My Storys");
        navArrNhatKi.add("Rating");
        navListNhatKiHome.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, navArrNhatKi);
        navListNhatKiHome.setAdapter(mAdapter);
        navListNhatKiHome.setOnItemClickListener(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayoutNhatKiHome, R.string.opendrawer, R.string.closedrawer);
        drawerLayoutNhatKiHome.setDrawerListener(actionBarDrawerToggle);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        fragmentManager = getSupportFragmentManager();
        loadSelection(0);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.all) {
            return true;
        } else if (id == android.R.id.home) {
            if (drawerLayoutNhatKiHome.isDrawerOpen(navListNhatKiHome)) {
                drawerLayoutNhatKiHome.closeDrawer(navListNhatKiHome);
            } else {
                drawerLayoutNhatKiHome.openDrawer(navListNhatKiHome);
            }
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        loadSelection(position);
        drawerLayoutNhatKiHome.closeDrawer(navListNhatKiHome);
    }

    private void loadSelection(int position) {
        navListNhatKiHome.setItemChecked(position, true);
        switch (position) {
            case 0:
                NhatKiHomeFragment nhatKiHomeFragment = new NhatKiHomeFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentNhatKiholder, nhatKiHomeFragment);
                fragmentTransaction.commit();
                break;
            case 1:
                AllNhatKiFragment allNhatKiFragment = new AllNhatKiFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentNhatKiholder, allNhatKiFragment);
                fragmentTransaction.commit();
                break;
            case 2:
                MyFragment1 myFragment1 = new MyFragment1();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentNhatKiholder, myFragment1);
                fragmentTransaction.commit();
                break;

            case 5:
                break;

        }
    }
}

