package com.example.databasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            SQLiteDatabase database = this.openOrCreateDatabase("users", MODE_PRIVATE, null);  // creating database with the name: users  // It only creates the database if it does not exist other wise it will open it.
            database.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR, age INT(3))");  // creates table with name users // with variables name, and age // INT(3) tells us the integer variable is a 3 digit number.

            database.execSQL("INSERT INTO users (name, age) VALUES ('Mad', 35)");
            database.execSQL("INSERT INTO users (name, age) VALUES ('Jack', 54)");
            database.execSQL("INSERT INTO users (name, age) VALUES ('Rob', 23)");
            database.execSQL("INSERT INTO users (name, age) VALUES ('Gwell', 16)");
            database.execSQL("INSERT INTO users (name, age) VALUES ('Gwell', 16)");
//            database.execSQL("DELETE FROM users WHERE age = 16 LIMIT 6 OFFSET 0 ");
            database.execSQL("DELETE FROM users WHERE age = 16 LIMIT 1");


            Cursor c = database.rawQuery("SELECT * FROM users WHERE age < 18 LIMIT 1", null);
            int nameIndex = c.getColumnIndex("name");
            int ageIndex = c.getColumnIndex("age");

            c.moveToFirst();
            while(c != null){
                Log.i("name", c.getString(nameIndex));
                Log.i("age", Integer.toString(c.getInt(ageIndex)));

                c.moveToNext();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
