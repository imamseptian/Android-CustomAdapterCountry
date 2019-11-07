package com.example.crudcountry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends ArrayAdapter<Country> {
    private Context nContext;
    int nResource;

    public CountryAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Country> objects) {
        super(context, resource, objects);
        this.nContext = context;
        nResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name = getItem(position).getCountryName();
        String capital = getItem(position).getCapital();

        Country country = new Country(name,capital);

        LayoutInflater inflater = LayoutInflater.from(nContext);
        convertView = inflater.inflate(nResource,parent,false);

        TextView tvName = (TextView) convertView.findViewById(R.id.textview1);
        TextView tvCapital = (TextView) convertView.findViewById(R.id.textview2);

        tvName.setText(name);
        tvCapital.setText(capital);

        return convertView;
    }
}
