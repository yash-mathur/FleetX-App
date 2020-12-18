package com.example.fleet;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class Repair extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore mFirestore;

    private EditText editSerDate, editService, editBill, editAmount, editDetails;
    private Button serviceSend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair);

        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        final String curdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

        final String curtime = new SimpleDateFormat("hh:mm:ss", Locale.getDefault()).format(new Date());

        String uid = user.getUid();
        final String email = user.getEmail();

        editSerDate = (EditText) findViewById(R.id.editSerDate);
        editService = (EditText) findViewById(R.id.editService);
        editBill = (EditText) findViewById(R.id.editBill);
        editAmount = (EditText) findViewById(R.id.editAmount);
        editDetails = (EditText) findViewById(R.id.editDetails);
        serviceSend = (Button) findViewById(R.id.serviceSend);

        serviceSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String SerDate = editSerDate.getText().toString();
                String Service = editService.getText().toString();
                String ServiceBill = editBill.getText().toString();
                String ServiceAmt = editAmount.getText().toString();
                String ServiceDetails = editDetails.getText().toString();

                Map<String, String> serviceMap = new HashMap<>();
                serviceMap.put("date", SerDate);
                serviceMap.put("service",Service);
                serviceMap.put("Bill", ServiceBill);
                serviceMap.put("Amount",ServiceAmt);
                serviceMap.put("Details",ServiceDetails);
                serviceMap.put("Email",email);
                serviceMap.put("curdate",curdate);
                serviceMap.put("curtime",curtime);

                mFirestore.collection("service").add(serviceMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(Repair.this, "Data Added", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        String error = e.getMessage();
                        Toast.makeText(Repair.this,"Failed" + error, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
}
