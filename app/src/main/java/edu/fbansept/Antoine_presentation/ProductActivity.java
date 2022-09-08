package edu.fbansept.Antoine_presentation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {

    private ArrayList<Product> productList = new ArrayList<>();
    JsonObjectRequest jsonArrayRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
         jsonArrayRequest = new JsonObjectRequest(
                "https://dummyjson.com/products",
                response -> {

                    productList = new ArrayList<>();

                    try {
                        JSONArray jsonProductList = response.getJSONArray("products");

                        for(int i = 0; i < jsonProductList.length(); i++ ) {
                            try {
                                JSONObject json = jsonProductList.getJSONObject(i);
                                Product produit = new Product(json);
                                productList.add(produit);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        RecyclerView rvProductList = findViewById(R.id.rvProductList);
                        rvProductList.setLayoutManager(new LinearLayoutManager(this));
                        rvProductList.setAdapter(new ProductListAdapter(productList));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Log.d("volley",error.toString())
        );

        RequestManager.getInstance(this).addToRequestQueue(jsonArrayRequest);



    }
    @Override
    protected void onStop() {
        super.onStop();
        cancelRequest();
    }

    private void cancelRequest() {
        if (jsonArrayRequest
                != null) {
            jsonArrayRequest.cancel();
        }
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
}