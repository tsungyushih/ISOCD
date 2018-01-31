package com.example.tsung.isocd;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.tsung.isocd.Fragment.Main_BlankFragment;
import com.example.tsung.isocd.Fragment.Main_BlankFragment1;
import com.example.tsung.isocd.Fragment.Main_BlankFragment2;
import com.example.tsung.isocd.Fragment.Main_BlankFragment3;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        ArrayList<String> arraylist = new ArrayList<>();
        arraylist.add("地震情報");
        arraylist.add("颱風警報");
        arraylist.add("空氣品質預報與紫外線即時觀測");
        arraylist.add("停班停課資訊");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                arraylist);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i)
                {
                    case 0:
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.layout1, new Main_BlankFragment());
                        ft.commit();
                        break;
                    case 1:
                        FragmentTransaction ft1 = getFragmentManager().beginTransaction();
                        ft1.replace(R.id.layout1, new Main_BlankFragment1());
                        ft1.commit();
                        break;
                    case 2:
                        FragmentTransaction ft2 = getFragmentManager().beginTransaction();
                        ft2.replace(R.id.layout1,new Main_BlankFragment2());
                        ft2.commit();
                        break;
                    case 3:
                        FragmentTransaction ft3 = getFragmentManager().beginTransaction();
                        ft3.replace(R.id.layout1, new Main_BlankFragment3());
                        ft3.commit();
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
