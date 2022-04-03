package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONObject;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView buy;
    TextView sell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // this hides the upper part
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportActionBar().hide(); // this hides the action bar
        setContentView(R.layout.activity_main);

        buy = findViewById(R.id.textView);
        sell = findViewById(R.id.textView3);

        String url = "http://192.168.0.101/Currency_Converter/get.php"; // url of the API (get_updated_rate)

        DownloadTask task = new DownloadTask();
        task.execute(url); //

    }

    public class DownloadTask extends AsyncTask<String, Void, String> {

        // establishing a connection with the backend through the url
        // reading content of the url
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

            // get the JSON object and convert it to a string
            // setting text views as updated rate of buy and sell
            try{
                JSONObject json = new JSONObject(s);
                String buy_rate = json.getString("buy_rate");
                String sell_rate = json.getString("sell_rate");
                buy.setText("Buy 1 USD at " + buy_rate + " LBP");
                sell.setText("Sell 1 USD at " + sell_rate + " LBP");

            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }

    // will take the user to the MainActivity2.java which is the calculator page
    public void goToCalculator (View view){
        Intent obj = new Intent(getApplicationContext(), MainActivity2.class);
        startActivity(obj);

    }

    // refreshing the page to get the updated rate
    public void refresh(View view){
        String url = "http://192.168.0.101/Currency_Converter/get.php";

        DownloadTask task = new DownloadTask();
        task.execute(url);
    }

}
