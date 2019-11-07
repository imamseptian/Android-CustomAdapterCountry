package com.example.crudcountry;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class CRUD {
    private Context countryContext;
    private ArrayList<Country> countries=new ArrayList<>();


    public void save(String countryName,String capitalName)
    {
        Country country = new Country(countryName,capitalName);
        countries.add(country);
    }

    public ArrayList<Country> getCountries()
    {
        return countries;
    }




    public Boolean update(int position,String newCountryName,String newCapitalName)
    {
        try {
            countries.remove(position);

            Country country = new Country(newCountryName,newCapitalName);

            countries.add(position,country);

            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean delete(int position)
    {
        try {
            countries.remove(position);

            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;

        }
    }
}
