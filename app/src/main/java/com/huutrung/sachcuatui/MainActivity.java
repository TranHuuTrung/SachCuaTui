package com.huutrung.sachcuatui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    ImageButton   imgbtn_book;
    Button        btn_Nhatki ;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgbtn_book = (ImageButton) findViewById(R.id.imgbtn_book);
        btn_Nhatki = (Button) findViewById(R.id.btn_Nhatki);

        imgbtn_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListBookActivity.class);
                startActivity(intent);
            }
        });

        btn_Nhatki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NhatKiActivity.class);
                startActivity(intent);
            }
        });



    /* public void showRating(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Rating");
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
    }*/
    }
}