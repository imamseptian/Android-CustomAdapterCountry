package com.example.crudcountry;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    ArrayAdapter<Country> adapter;
    CRUD crud=new CRUD();
    Dialog d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lv= (ListView) findViewById(R.id.countryList);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(d != null) {
                    if(!d.isShowing())
                    {
                        displayInputDialog(i);
                    }else
                    {
                        d.dismiss();
                    }
                }
            }
        });

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                displayInputDialog(-1);
            }
        });
    }

    private void displayInputDialog(final int pos)
    {
        d=new Dialog(this);
        d.setTitle("LISTVIEW CRUD");
        d.setContentView(R.layout.input_dialog);

        final EditText countryEditTxt= (EditText) d.findViewById(R.id.countryEditText);
        final EditText capitalEditTxt= (EditText) d.findViewById(R.id.capitalEditText);

        Button addBtn= (Button) d.findViewById(R.id.addBtn);
        Button updateBtn= (Button) d.findViewById(R.id.updateBtn);
        Button deleteBtn= (Button) d.findViewById(R.id.deleteBtn);

        if(pos== -1)
        {
            addBtn.setEnabled(true);
            updateBtn.setEnabled(false);
            deleteBtn.setEnabled(false);
        }else
        {
            addBtn.setEnabled(true);
            updateBtn.setEnabled(true);
            deleteBtn.setEnabled(true);
            countryEditTxt.setText(crud.getCountries().get(pos).getCountryName());
            capitalEditTxt.setText(crud.getCountries().get(pos).getCapital());
        }

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GET DATA
                String countryName=countryEditTxt.getText().toString();
                String capitalName=capitalEditTxt.getText().toString();

                //VALIDATE
                if(countryName.length()>0 && countryName != null && capitalName.length()>0 && capitalName != null)
                {
                    //save
                    crud.save(countryName,capitalName);
                    countryEditTxt.setText("");
                    capitalEditTxt.setText("");
                    adapter=new CountryAdapter(MainActivity.this,R.layout.list_item,crud.getCountries());
                    lv.setAdapter(adapter);

                }else
                {
                    Toast.makeText(MainActivity.this, "Field cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GET DATA
                String newCountryName=countryEditTxt.getText().toString();
                String newCapitalName=capitalEditTxt.getText().toString();

                //VALIDATE
                if(newCountryName.length()>0 && newCountryName != null && newCapitalName.length()>0 && newCapitalName != null)
                {
                    //save
                    if(crud.update(pos,newCountryName,newCapitalName))
                    {
                        countryEditTxt.setText(newCountryName);
                        capitalEditTxt.setText(newCapitalName);
                        adapter=new CountryAdapter(MainActivity.this,R.layout.list_item,crud.getCountries());
                        lv.setAdapter(adapter);
                    }

                }else
                {
                    Toast.makeText(MainActivity.this, "Field cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //DELETE
                if( crud.delete(pos))
                {
                    countryEditTxt.setText("");
                    capitalEditTxt.setText("");
                    adapter=new CountryAdapter(MainActivity.this,R.layout.list_item,crud.getCountries());
                    lv.setAdapter(adapter);
                }
            }
        });

        d.show();
    }
}
