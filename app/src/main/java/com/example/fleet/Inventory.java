package com.example.fleet;

import android.content.Intent;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Inventory extends AppCompatActivity {

    private FirebaseFirestore mFirestore;
    private FirebaseAuth mAuth;


    private EditText editID, editName, editFrom, editTo, editPerson, editContact, editDate;
    private Button buttonSend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        mFirestore = FirebaseFirestore.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        //Getting Current Date
        final String curdate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        final String curtime = new SimpleDateFormat("hh:mm:ss", Locale.getDefault()).format(new Date());
        //Getting current user info
        final String uid = user.getUid();
        final String email = user.getEmail();
        editID = (EditText) findViewById(R.id.editID);
        editName = (EditText) findViewById(R.id.editName);
        editFrom = (EditText) findViewById(R.id.editFrom);
        editTo = (EditText) findViewById(R.id.editTo);
        editPerson = (EditText) findViewById(R.id.editPerson);
        editContact = (EditText) findViewById(R.id.editContact);
        editDate = (EditText) findViewById(R.id.editDate);
        buttonSend = (Button) findViewById(R.id.buttonSend);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = editID.getText().toString();
                String name = editName.getText().toString();
                String from = editFrom.getText().toString();
                String to = editTo.getText().toString();
                String person = editPerson.getText().toString();
                String contact = editContact.getText().toString();
                String date = editDate.getText().toString();


                Map<String,String> invMap = new HashMap<>();
                invMap.put("id", id);
                invMap.put("name", name);
                invMap.put("from", from);
                invMap.put("to", to);
                invMap.put("person", person);
                invMap.put("contact", contact);
                invMap.put("date", date);
                invMap.put("email",email);
                invMap.put("curdate",curdate);
                invMap.put("curtime",curtime);

                mFirestore.collection("inventory").add(invMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(Inventory.this, "Data Added",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        String error = e.getMessage();
                        Toast.makeText(Inventory.this, "Failed" + error, Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });


    }
}

