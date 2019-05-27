package com.sample.project1;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class EmergencyActivity extends AppCompatActivity {

    Set<String> set , set2;
    String array[] , phone[];
    SharedPreferences sharedPreferences ;
    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    public static final String Phone = "phoneKey";

    ListView listView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);

        sharedPreferences = getSharedPreferences(mypreference , Context.MODE_PRIVATE);
        set = new HashSet<>();
        set = sharedPreferences.getStringSet(Name , null);
        if (set == null) {
            array = new String[1];
        }
        else
            array = new String[set.size()];
        set2 = new HashSet<>();
        set2 = sharedPreferences.getStringSet(Phone , null);
        if (set2 == null)
            phone = new String[1];
        else
            phone = new String[set2.size()];
        int k=0;
        if (set != null){
            for (String i : set){
                array[k] = i;
                k++;
            }
        }
        int l=0 ;
        if (set2 != null){
            for (String i : set2){
                phone[l] = i;
                l++;
            }
        }
        listView = findViewById(R.id.listView);

        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);
    }


    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return array.length ;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.custom_layout,null);
            TextView name = convertView.findViewById(R.id.textView3);
            TextView phoneT = convertView.findViewById(R.id.textView4);
            name.setText(array[position]);
            phoneT.setText(phone[position]);
            return convertView;
        }
    }
}


