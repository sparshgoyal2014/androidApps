package com.example.firebasetesting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private DatabaseReference databaseReference2;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.setValue("my name is Firebase");


        databaseReference = FirebaseDatabase.getInstance().getReference("Key1");
        databaseReference.setValue("my name is Firebase 2");

        databaseReference = FirebaseDatabase.getInstance().getReference("Key1");
        databaseReference.setValue("Hi, My name is Firebase");

        databaseReference.child("name").child("firstName").setValue("Spartsh");

//        databaseReference = FirebaseDatabase.getInstance().getReference();
//        databaseReference.setValue("hello");

        databaseReference = FirebaseDatabase.getInstance().getReference("key2");
        databaseReference.setValue("Your Roll Number");

        databaseReference = FirebaseDatabase.getInstance().getReference("key1").child("name").child("firstName");
        databaseReference.push().setValue("Sairas");

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("newKey");

        /*********//*********//*********//*********//*********//*********//*********/

        databaseReference2 = FirebaseDatabase.getInstance().getReference("you referencing me!");
        databaseReference2.setValue("hi, my name is spartsh good to see you! :)");

        databaseReference2 = FirebaseDatabase.getInstance().getReference("You referencing me to make my child").child("hi I am child");
        databaseReference2.setValue("Am I Cute or not");
        databaseReference2.push().setValue(1);
        databaseReference2.push().setValue(2);

//        int[] arr = {1,2,3,4,5};
//        ArrayList arrayList = new ArrayList();
//        arrayList.add(1);
//        arrayList.add(2);
//        arrayList.add(3);
//        databaseReference2.push().setValue(arrayList);
//        databaseReference2.setValue("Am I Cute or not");
//        databaseReference2.push().setValue(1);



    }
}
