package com.example.apirest;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends ArrayAdapter {

    private static final String TAG = "CategoriaAdapter";
    private static final String URL = "https://itunes.apple.com/us/rss/topfreeapplications/limit=20/json";

    RequestQueue requestQueue;
    List<AppModel> itemsApp;

    public CategoryAdapter (Context context){
        super(context,0);

        requestQueue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, URL, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                itemsApp = parseJSON(response);
                notifyDataSetChanged();
                Log.e(TAG,  response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.toString());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        //Referencia del View Procesado
        View listItemView;

        // Comprobando si el View no Existe
        listItemView = null == convertView?layoutInflater.inflate(
                R.layout.adapter_category,
                parent,
                false) : convertView;

        // Obtenemos el Item Actual
        AppModel item = itemsApp.get(position);

        // Obtener Views
        TextView textName = listItemView.findViewById(R.id.textName);
        TextView textCategory = listItemView.findViewById(R.id.textCategory);
        final ImageView image = listItemView.findViewById(R.id.image);
        TextView textSummary = listItemView.findViewById(R.id.textSummary);

        //Actualización de los Views
        textName.setText(item.getName());
        Log.e("nombre", item.getName());
        textCategory.setText(item.getCategory());
        textSummary.setText(item.getSummary());

        return listItemView;
    }

    @Override
    public int getCount() {
        return itemsApp!=null ? itemsApp.size(): 0;
    }

    public List<AppModel> parseJSON (JSONObject jsonObject) {

        List<AppModel> applist = new ArrayList<>();
        try {
            //Obtener el Array del Objeto
            JSONObject feedJSON = jsonObject.getJSONObject("feed");
            JSONArray entryJSON = feedJSON.getJSONArray("entry");

            for (int i = 0; i<entryJSON.length(); i++){
                try {
                    JSONObject entryObject = entryJSON.getJSONObject(i);
                    JSONObject nameJSON = entryObject.getJSONObject("im:name");
                    JSONObject summaryJSON = entryObject.getJSONObject("summary");

                    JSONObject categoryJSON = entryObject.getJSONObject("category");
                    JSONObject attributescategoryJSON = categoryJSON.getJSONObject("attributes");


                    AppModel category = new AppModel(
                            nameJSON.getString("label"),
                            attributescategoryJSON.getString("label"),
                            summaryJSON.getString("label")
                    );
                    applist.add(category);
                }catch (JSONException e){
                    Log.e(TAG, "Error de Parsing: "+e.getMessage());
                }
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return applist;
    }


}
