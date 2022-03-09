package com.example.volleydemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // initialize variable
    ListView listView;
    ArrayList<MainData> dataArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // assign variable
        listView = findViewById(R.id.list_view);

        //url
        String url = "https://picsum.photos/v2/list";

        // initialize progress dialog
        ProgressDialog dialog = new ProgressDialog(this);
        // set message
        dialog.setMessage("Please wait...");
        //set on cancelable
        dialog.setCancelable(true);
        // show dialog
        dialog.show();

        // initialize string request
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // check condition
                if (response != null) {
                    //when response is not null
                    // dismiss progress dialog
                    dialog.dismiss();

                    try {
                        //initialize response json array
                        JSONArray jsonArray = new JSONArray(response);
                        // parse array
                        parseArray(jsonArray);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // display toast
                Toast.makeText(getApplicationContext(), error.toString(),
                        Toast.LENGTH_SHORT).show();

            }
        });
        // initialize request queue
        RequestQueue queue = Volley.newRequestQueue(this);
        // add request
        queue.add(request);
    }

    private void parseArray(JSONArray jsonArray) {
        // use for loop
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                // initialize json object
                JSONObject object = jsonArray.getJSONObject(i);
                // initialize main data
                MainData data = new MainData();
                // set name
                data.setName(object.getString("author"));
                // set image
                data.setImage(object.getString("download_url"));
                // add data in array list
                dataArrayList.add(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            // set adapter
            listView.setAdapter(new BaseAdapter() {
                @Override
                public int getCount() {
                    return dataArrayList.size();
                }

                @Override
                public Object getItem(int i) {
                    return null;
                }

                @Override
                public long getItemId(int i) {
                    return 0;
                }

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    // initialize view
                    View view = getLayoutInflater().inflate(R.layout.item_main, null
                    );
                    // initialize main data
                    MainData data = dataArrayList.get(position);

                    // initialize and assign  variable
                    ImageView imageView = view.findViewById(R.id.image_view);
                    TextView textView = view.findViewById(R.id.text_view);

                    // set image on image view
                    Glide.with(getApplicationContext())
                            .load(data.getImage())
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(imageView);
                    // set name on text view
                    textView.setText(data.getName());


                    // return view
                    return view;
                }

            });
        }


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(MainActivity.this, "image clicked" + " " + i + "  " + dataArrayList.get(i), Toast.LENGTH_SHORT).show();
                //  Toast.makeText(MainActivity.this, "image clicked"+"  "+i, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, secondactivity.class);
                intent.putExtra("key", dataArrayList.get(i));
                startActivity(intent);


            }

        });
    }
}