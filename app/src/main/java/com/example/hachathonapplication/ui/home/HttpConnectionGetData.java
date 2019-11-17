package com.example.hachathonapplication.ui.home;


import android.os.AsyncTask;
import android.util.Log;

import com.example.hachathonapplication.ui.home.HomeFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnectionGetData extends AsyncTask<Void, Void, String> {
    public  HomeFragment previos;
    String responseD=null;

    HttpConnectionGetData(HomeFragment instance){
        previos = instance;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        responseD=null;
    }

    @Override
    protected String doInBackground(Void... params) {
        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        // Will contain the raw JSON response as a string.
        String forecastJsonStr = null;




        try {

            URL url = new URL("http://starlord.hackerearth.com/studio");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("Content-Type", "application/json");

            int responseCode = urlConnection.getResponseCode();

            Log.d("Responce", String.valueOf(responseCode));

//            OutputStream os = urlConnection.getOutputStream();
//            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
//            osw.write("{  " +
//                    "      \"song\":\"Aaj Rung\"," +
//                    "      \"url\":\"http://hck.re/H5nMm3\"," +
//                    "      \"artists\":\"Amjad Sabri,  Rahat Fateh Ali Khan\"," +
//                    "      \"cover_image\":\"http://hck.re/U1bRnt\"" +
//                    "   }");
//            osw.flush();
//            osw.close();
//            os.close();


            urlConnection.connect();
            int lengthOfFile = urlConnection.getContentLength();
            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                return null;
            }
            forecastJsonStr = buffer.toString();
            Log.e("Json1", forecastJsonStr);

            responseD=forecastJsonStr;



            return forecastJsonStr;
        } catch (IOException e) {
            Log.e("PlaceholderFragment", "Error ", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("PlaceholderFragment", "Error closing stream", e);
                }
            }
        }
        return forecastJsonStr;
    }

    public String getResponse(){
        return responseD;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        //adapter.notifyDataSetChanged();
        previos.callback();
    }
}