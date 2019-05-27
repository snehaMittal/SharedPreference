package com.sample.project1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    Button emergency , add ;
    SharedPreferences sharedPreferences ;
    private TextView resultText;

    Set<String> nameSet, phoneSet ;

    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    public static final String Phone = "phoneKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emergency = findViewById(R.id.emergency);
        add = findViewById(R.id.add);
        sharedPreferences = getSharedPreferences(mypreference , Context.MODE_PRIVATE);

        if(nameSet == null){

            nameSet = new HashSet<>();
            phoneSet = new HashSet<>();

        }
        emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent( MainActivity.this , EmergencyActivity.class);
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputDialog();
            }
        });
    }

    protected void showInputDialog() {

        final SharedPreferences.Editor editor = sharedPreferences.edit();
        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
        View promptView = layoutInflater.inflate(R.layout.input_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setView(promptView);

        final EditText editText = (EditText) promptView.findViewById(R.id.edittext);
        final EditText editText2 = (EditText) promptView.findViewById(R.id.edittext2);
        // setup a dialog window
        alertDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String str1 = editText.getText().toString() ;
                        String str2 = editText2.getText().toString() ;
                        if (str1.trim().equals("")){
                            Toast.makeText(MainActivity.this, "Please enter the name ", Toast.LENGTH_SHORT).show();
                        }
                        else if (str2.trim().equals("")){
                            Toast.makeText(MainActivity.this, "Please enter phone number ", Toast.LENGTH_SHORT).show();
                        }
                        else if (str2.length() != 10){
                            Toast.makeText(MainActivity.this , "Enter Correct Phone Number", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            editor.clear();
                            nameSet = sharedPreferences.getStringSet(Name , new HashSet<String>());
                            nameSet.add(str1 );
                            phoneSet = sharedPreferences.getStringSet(Phone ,new HashSet<String>());
                            phoneSet.add(str2);
                            editor.putStringSet(Name , nameSet);
                            editor.putStringSet(Phone , phoneSet);
                            editor.apply();
                            Toast.makeText(MainActivity.this, " Contact Saved", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }
}
