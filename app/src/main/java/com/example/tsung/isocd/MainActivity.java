package com.example.tsung.isocd;

import android.Manifest;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.tsung.isocd.Fragment.DetailActivity;
import com.example.tsung.isocd.Fragment.Main_BlankFragment;
import com.example.tsung.isocd.Fragment.Main_BlankFragment1;
import com.example.tsung.isocd.Fragment.Main_BlankFragment2;
import com.example.tsung.isocd.Fragment.Main_BlankFragment3;
import com.example.tsung.isocd.Fragment.Main_BlankFragment4;
import com.example.tsung.isocd.Fragment.MyAdapter;
import com.example.tsung.isocd.Fragment.MyHandler;


import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

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
        arraylist.add("氣象情報與生活新聞");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                arraylist);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {
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
                        ft2.replace(R.id.layout1, new Main_BlankFragment2());
                        ft2.commit();
                        break;
                    case 3:
                        FragmentTransaction ft3 = getFragmentManager().beginTransaction();
                        ft3.replace(R.id.layout1, new Main_BlankFragment3());
                        ft3.commit();
                        break;
                    case 4:
                        FragmentTransaction ft4 = getFragmentManager().beginTransaction();
                        ft4.replace(R.id.layout1, new Main_BlankFragment4());
                        ft4.commit();
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }


}
