package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // this hides the upper part
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportActionBar().hide(); // this hides the action bar
        setContentView(R.layout.activity_main);

        String url = "http://192.168.0.101/Currency_Converter/get.php";

        DownloadTask task = new DownloadTask();
        task.execute(url);

    }

    public void goToCalculator (View view){
        Intent obj = new Intent(getApplicationContext(), MainActivity2.class);
        startActivity(obj);
    }

    public class DownloadTask extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... urls){
            String result = "";
            URL url;
            HttpURLConnection http;

            try{
                url = new URL(urls[0]);
                http = (HttpURLConnection) url.openConnection();

                InputStream in = http.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while( data != -1){
                    char current = (char) data;
                    result += current;
                    data = reader.read();

                }
            }catch(Exception e){
                e.printStackTrace();
                return null;
            }

            return result;
        }


        protected void onPostExecute(String s){
            super.onPostExecute(s);

            try{
                JSONObject json = new JSONObject(s);
                String buy = json.getString("buy_rate");
                String sell = json.getString("sell_rate");
                Log.i("buy_rate", buy);
                Log.i("sell_rate", sell);

            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }


}

    //
    URL url = new URL ("https://reqres.in/api/users");
    //
    HttpURLConnection con = (HttpURLConnection)url.openConnection();
    //
    con.setRequestMethod("POST");
    //
    con.setRequestProperty("Content-Type", "application/json; utf-8");
    //
    con.setRequestProperty("Accept", "application/json");
    //
    con.setDoOutput(true);
    //
    String jsonInputString = "{"name": "Upendra", "job": "Programmer"}";
    //
    try(OutputStream os = con.getOutputStream()) {
        byte[] input = jsonInputString.getBytes("utf-8");
        os.write(input, 0, input.length);
    }
    //
    try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
        StringBuilder response = new StringBuilder();
        String responseLine = null;
        while ((responseLine = br.readLine()) != null) {
            response.append(responseLine.trim());
        }
        System.out.println(response.toString());
    }