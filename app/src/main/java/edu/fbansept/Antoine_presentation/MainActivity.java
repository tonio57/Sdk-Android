package edu.fbansept.Antoine_presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button button1;
    private Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.imageView = (ImageView) this.findViewById(R.id.imageView);

        this.button1 = (Button) this.findViewById(R.id.button1);
        this.button2 = (Button) this.findViewById(R.id.button2);

        this.button1.setOnClickListener(new  Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                showImage1();
            }
        });

        this.button2.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                showImage2();
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String  TAG="titre";
        Intent intent;
        switch (item.getItemId()) {
            case R.id.home:
                Log.i(TAG, "Clic sur le menu Home");
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_produits:
                Log.i(TAG, "Clic sur le menu produits");
                intent = new Intent(this, ProductActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_département:
                Log.i(TAG, "Clic sur le menu département");
                intent = new Intent(this, RegionActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void showImage1() {
        this.imageView.setImageResource(R.drawable.img);
    }

    private void showImage2() {
        this.imageView.setImageResource(R.drawable.img_1);
    }
}