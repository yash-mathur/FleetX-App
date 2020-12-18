package com.example.fleet;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.PermissionRequest;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    ImageButton androidImageButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        androidImageButton = (ImageButton) findViewById(R.id.imageButton_inv);
        androidImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(MainActivity.this, Inventory.class);
                startActivity(intentLoadNewActivity);
            }
        });
        androidImageButton = (ImageButton) findViewById(R.id.imageButton_repair);
        androidImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(MainActivity.this, Repair.class);
                startActivity(intentLoadNewActivity);
            }
        });
        androidImageButton = (ImageButton) findViewById(R.id.imageButton_invoice);
        androidImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(MainActivity.this, Invoice.class);
                startActivity(intentLoadNewActivity);
            }
        });
        androidImageButton = (ImageButton) findViewById(R.id.imageButton_notes);
        androidImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(MainActivity.this, Notes.class);
                startActivity(intentLoadNewActivity);
            }
        });
        androidImageButton = (ImageButton) findViewById(R.id.imageButton_fuel);
        androidImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(MainActivity.this, Fuel.class);
                startActivity(intentLoadNewActivity);
            }
        });
        androidImageButton = (ImageButton) findViewById(R.id.imageButton_reminder);
        androidImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLoadNewActivity = new Intent(MainActivity.this, Reminder.class);
                startActivity(intentLoadNewActivity);
            }
        });
    }
}
