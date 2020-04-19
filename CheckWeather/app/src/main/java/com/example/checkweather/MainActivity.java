package com.example.checkweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText cityName;
    TextView resultTextView;

    public void findWeather(View view){
        Log.i("City Name", cityName.getText().toString());

        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(cityName.getWindowToken(), 0);  // To hide the Keyboard when the button is tapped


        DownloadTask downloadTask = new DownloadTask();
        downloadTask.execute("https://samples.openweathermap.org/data/2.5/weather?q=" + cityName.getText().toString() + "&appid=b6907d289e10d714a6e88b30761fae22");
    }

    public class DownloadTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);

                try {
                    urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream inputStream = urlConnection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(inputStream);

                    int data = reader.read();

                    while(data != -1){
                        char current = (char)data;
                        result += current;

                        data = reader.read();

                    }

                    return result;
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Could not find weather", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {

                String message = "";
                JSONObject jsonObject = new JSONObject(result);

                String weatherInfo = jsonObject.getString("weather");  // extract the "weather" part of the JSON Object.
                Log.i("Weather content", weatherInfo);

                JSONArray array = new JSONArray(weatherInfo);
                for(int i=0; i<array.length(); i++){
                    JSONObject jsonPart = array.getJSONObject(i);

                    String main = "";
                    String description = "";

                    main = jsonPart.getString("main");
                    description = jsonPart.getString("description");

                    if(main != "" && description != ""){
                        message += main + ": " + description + "\r\n";
                    }

                    Log.i("main", jsonPart.getString("main"));
                    Log.i("description", jsonPart.getString("description"));

                }

                if(message != ""){
                    resultTextView.setText(message);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
//            Log.i("Website Content:", result);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityName = findViewById(R.id.cityName);
        resultTextView = findViewById(R.id.resultTextView);

    }
}
