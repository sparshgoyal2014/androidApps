package com.example.memorableplaces2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


//class Animal{
//
//    public int number = 5;
//    public Animal(int number){
//        this.number = number;
//    }
//    public void display(){
//        System.out.println("This is Animal Class");
//    }
//}



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);


        final ArrayList<String> places = new ArrayList<>();

//        final ArrayList arrayList = new ArrayList();
//        arrayList.add(new Animal(1));
//        arrayList.add(new Animal(2));
//        arrayList.add(new Animal(3));
////        arrayList.add("HelloWorld");
//
//        places.add("Add a new place...");
//
//        ArrayAdapter<Animal> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);

        places.add("Add new Places.....");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, places);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, Integer.toString(position), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                intent.putExtra("place number", position);

                startActivity(intent);
            }
        });
    }
}
