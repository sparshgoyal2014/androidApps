package com.example.newsreader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> titles = new ArrayList<>();
    ArrayList<String> contents = new ArrayList<>();

    ArrayAdapter arrayAdapter;
    SQLiteDatabase articlesDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, titles);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ArticleActivity.class);
                intent.putExtra("content", contents.get(position));

                startActivity(intent);
            }
        });


        articlesDB = this.openOrCreateDatabase("Articles", MODE_PRIVATE, null);

        articlesDB.execSQL("CREATE TABLE IF NOT EXISTS articles2 (id INTEGER PRIMARY KEY, articleId INTEGER, title VARCHAR, content VARCHAR)");

        updateListView();

        DownloadTask downloadTask = new DownloadTask();
        try{
            downloadTask.execute("https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty");
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void updateListView(){
        Cursor c = articlesDB.rawQuery("SELECT * FROM articles2", null);
        int contentIndex = c.getColumnIndex("content");
        int titleIndex = c.getColumnIndex("title");

        if(c.moveToFirst()){
            titles.clear();
            contents.clear();

            do{
                titles.add(c.getString(titleIndex));
                contents.add(c.getString(contentIndex));
            }while(c.moveToNext());

            arrayAdapter.notifyDataSetChanged();
        }
    }

    public class DownloadTask extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {

            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(inputStream);

                int data = reader.read();

                while(data != -1){
                    char currentChar = (char) data;
                    result += currentChar;
                    data = reader.read();
                }

//                Log.i("url content", result);
                JSONArray jsonArray = new JSONArray(result);

                int numberOfItems = 20;
                if(jsonArray.length() < 20){
                    numberOfItems = jsonArray.length();
                }
                articlesDB.execSQL("DELETE FROM articles2");
                for(int i=0; i<numberOfItems; i++){
                    String articlesId = jsonArray.getString(i);
                   url = new URL("https://hacker-news.firebaseio.com/v0/item/" + articlesId + ".json?print=pretty");
                   urlConnection = (HttpURLConnection) url.openConnection();
                   inputStream = urlConnection.getInputStream();
                   reader = new InputStreamReader(inputStream);
                   data = reader.read();

                   String articleInfo = "";

                   while(data != -1){
                       char current = (char) data;
                       articleInfo += current;
                       data = reader.read();
                   }


                   JSONObject jsonObject = new JSONObject(articleInfo);
                   Log.i("infoOfObject", jsonObject.toString());

                   if(!jsonObject.isNull("title") && !jsonObject.isNull("url")){
                       String articleTitle = jsonObject.getString("title");
                       String articleUrl = jsonObject.getString("url");
                       Log.i("info", articleTitle + articleUrl);

                       url = new URL(articleUrl);
                       urlConnection = (HttpURLConnection) url.openConnection();
                       inputStream = urlConnection.getInputStream();
                       reader = new InputStreamReader(inputStream);
                       data = reader.read();

                       String articleContent = "";

                       while(data != -1){
                           char current = (char) data;
                           articleContent += current;
                           data = reader.read();
                       }

                       Log.i("content", articleContent);

                       String sql = "INSERT INTO articles2 (articleId, title, content) VALUES (? , ? , ?)";
                       SQLiteStatement statement = articlesDB.compileStatement(sql);
                       statement.bindString(1, articlesId);
                       statement.bindString(2, articleTitle);
                       statement.bindString(3, articleContent);

                       statement.execute();


                   }


//                   Log.i("ArticleInfo", articleInfo);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            updateListView();
        }
    }
}
