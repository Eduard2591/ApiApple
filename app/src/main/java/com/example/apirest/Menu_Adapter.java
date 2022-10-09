package com.example.apirest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;



public class Menu_Adapter extends ArrayAdapter {

    String [] data;

    public Menu_Adapter(Context context, String [] data) {
        super(context, R.layout.adapter_menu, data);
        this.data= data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        //referencia del View Procesado
        View ListItemView;
        ListItemView = null == convertView ? layoutInflater.inflate(
                R.layout.adapter_menu,
                parent,
                false): convertView;

        TextView textTitle = ListItemView.findViewById(R.id.textTitle);
        final ImageView imagenMenu = ListItemView.findViewById(R.id.imageTitle);

        textTitle.setText(data[position]);
        if (position==0){
            imagenMenu.setImageResource(R.drawable.phone);
        }else{
            imagenMenu.setImageResource(R.drawable.compu);
        }


        return ListItemView;
    }
}
