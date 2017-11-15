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

import com.huutrung.sachcuatui.Book.HomeFragment;
import com.huutrung.sachcuatui.Book.MyFragment1;
import com.huutrung.sachcuatui.Book.MyFragment2;
import com.huutrung.sachcuatui.Book.MyFragment3;

import java.util.ArrayList;

public class ListBookActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawerLayout;
    ListView navList;
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_book);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navList = (ListView) findViewById(R.id.navList);

        ArrayList<String> navArr = new ArrayList<String>();
        navArr.add("Home");
        navArr.add("How to Read");
        navArr.add("WriteStory");
        navArr.add("Test");
        navArr.add("Rating");
        navList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, navArr);
        navList.setAdapter(adapter);
        navList.setOnItemClickListener(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.opendrawer, R.string.closedrawer);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        fragmentManager = getSupportFragmentManager();
        loadSelection(0);
    }

    private void loadSelection(int i) {
        navList.setItemChecked(i, true);
        switch (i) {
            case 0:
                HomeFragment homeFragment = new HomeFragment();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentholder, homeFragment);
                fragmentTransaction.commit();
                break;
            case 1:
                MyFragment1 myFragment1 = new MyFragment1();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentholder, myFragment1);
                fragmentTransaction.commit();
                break;
            case 2:
                MyFragment2 myFragment2 = new MyFragment2();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentholder, myFragment2);
                fragmentTransaction.commit();
                break;
            case 3:
                MyFragment3 myFragment3 = new MyFragment3();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentholder, myFragment3);
                fragmentTransaction.commit();
                break;
            case 5:
                break;

        }
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
            if (drawerLayout.isDrawerOpen(navList)) {
                drawerLayout.closeDrawer(navList);
            } else {
                drawerLayout.openDrawer(navList);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        loadSelection(position);
        drawerLayout.closeDrawer(navList);
    }

}