package edu.fbansept.Antoine_presentation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegionActivity extends AppCompatActivity {
    private static final Object REQUEST_TAG = new Object();
    private static final String URL_PATTERN = "https://geo.api.gouv.fr/departements/%s?fields=nom,code,region";
    private RequestQueue requestQueue;
    private EditText codeDepartementEdit;
    private TextView departementResultatView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region);
        codeDepartementEdit = findViewById(R.id.codeDepartement);
        departementResultatView = findViewById(R.id.departementResultat);
        requestQueue = Volley.newRequestQueue(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        cancelRequest();
    }

    private void cancelRequest() {
        if (requestQueue != null) {
            requestQueue.cancelAll(REQUEST_TAG);
        }
    }

    public void search(View view) {
        String codeDepartement = this.codeDepartementEdit.getText().toString();
        if (codeDepartement.isEmpty()) {
            return;
        }
        if (codeDepartement.length() == 1) {
            codeDepartement = "0" + codeDepartement;
        }
        sendRequest(codeDepartement);
    }

    private void sendRequest(String codeDepartement) {
        cancelRequest();
        departementResultatView.setText("Chargement...");
        String url = String.format(URL_PATTERN, codeDepartement);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        fillDepartementResultat(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.networkResponse != null
                        && error.networkResponse.statusCode == 404) {
                    departementResultatView.setText("Pas de département trouvé. Veuillez vérifier le code saisi.");
                } else {
                    departementResultatView.setText("Erreur : " + error.getMessage());
                }
            }
        });
        request.setTag(REQUEST_TAG);
        requestQueue.add(request);
    }

    private void fillDepartementResultat(JSONObject jsonObject) {
        try {
            String nomDepartement = jsonObject.getString("nom");
            String codeDepartement = jsonObject.getString("code");
            JSONObject jsonRegion = jsonObject.getJSONObject("region");
            String nomRegion = jsonRegion.getString("nom");
            String msg = String.format("%s est le département avec le code %s. Ce département fait partie de la région %s.", nomDepartement, codeDepartement, nomRegion);
            departementResultatView.setText(msg);
        } catch (JSONException e) {
            departementResultatView.setText("Erreur : " + e.getMessage());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String TAG = "titre";
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