package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class MainActivity2 extends AppCompatActivity {

    TextView amount_to_convert;
    EditText amount;

    public class DownloadTask extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... arg0) {
            try {
                String lbp = (String) arg0[0];
                String usd = (String) arg0[1];

                String link = "http://192.168.1.7/Currency_Converter/get_amount.php";
                String data = URLEncoder.encode("lbp", "UTF-8") + "=" +
                        URLEncoder.encode(lbp, "UTF-8");
                data += "&" + URLEncoder.encode("usd", "UTF-8") + "=" +
                        URLEncoder.encode(usd, "UTF-8");

                URL url = new URL(link);
                URLConnection conn = url.openConnection();

                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                wr.write(data);
                wr.flush();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getSupportActionBar().hide();

        amount_to_convert = findViewById(R.id.textView2);
        amount = findViewById(R.id.editTextTextPersonName);

    }

    public void goToMain(View view) {

        Intent obj = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(obj);
    }

    public void fadeAmountToConvert(View view) {
        // to make the text disappear while entering values
        if(amount_to_convert.getText().toString().equals("The amount LBP or USD")){
            amount_to_convert.setText("");
        }
    }

    public void convertToLbp (View view){

        String usd = amount.getText().toString();
        String lbp = "0";

        DownloadTask task = new DownloadTask();
        task.execute(usd, lbp);
    }
    
    public void convertToUsd (View view){

        String lbp = amount.getText().toString();
        String usd = "0";

        DownloadTask task = new DownloadTask();
        task.execute(usd, lbp);
    }

}