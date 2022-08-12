package com.example.carparkee13;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity {

    ListView lvCarpark;
    AsyncHttpClient client;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvCarpark = findViewById(R.id.lvCarpark);
        client = new AsyncHttpClient();

    }

    @Override
    protected void onResume() {
        super.onResume();

        ArrayList<Carpark> alCarpark = new ArrayList<Carpark>();

        client.get("https://api.data.gov.sg/v1/transport/carpark-availability", new JsonHttpResponseHandler(){

            String total;
            String available;
            String type;

            @Override
            //onSuccess if response is successfully received by the app form the site, all the code within here will trigger
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {


                    JSONArray jsonArrItems = response.getJSONArray("items");

                    JSONObject firstObj = jsonArrItems.getJSONObject(0);


                    JSONArray jsonArrCarpark = firstObj.getJSONArray("carpark_data");

                    for (int i = 0; i < jsonArrCarpark.length(); i++) {
                        JSONObject jsonObjCarpark = jsonArrCarpark.getJSONObject(i);
                        JSONArray jsonArrinfo = jsonObjCarpark.getJSONArray("carpark_info");
                        JSONObject jsonObjCzero = jsonArrinfo.getJSONObject(0);

                        String total = jsonObjCzero.getString("total_lots");
                        String type = jsonObjCzero.getString("lot_type");
                        String available = jsonObjCzero.getString("lots_available");

                        Carpark carpark = new Carpark(total,type, available);
                        alCarpark.add(carpark);
                    }
                }
                catch(JSONException e){

                }

                ArrayAdapter<Carpark> aaCarpark= new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,alCarpark);
                lvCarpark.setAdapter(aaCarpark);
            }


        });
    }



}