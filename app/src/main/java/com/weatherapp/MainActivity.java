package com.weatherapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TextView textView1;
    DrawerLayout dLayout;
    ImageView cloudPic;

    private static final String APP_ID = "b5362c203397ce801d354458c2262485";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.weatherapp.R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(com.weatherapp.R.id.toolbar);
        setSupportActionBar(toolbar);
        textView1 = (TextView) findViewById(R.id.textView1);// text to show addresses
        cloudPic = (ImageView) findViewById(R.id.cloud_pic);

        cloudPic.setAlpha(.5f);

        //code for bottom action bar
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_item1:
                                selectedFragment = ItemOneFragment.newInstance();
                                break;
                            case R.id.action_item2:
                                selectedFragment = ItemTwoFragment.newInstance();
                                break;
                            case R.id.action_item3:
                                selectedFragment = ItemThreeFragment.newInstance();
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, ItemOneFragment.newInstance());
        transaction.commit();

        //Used to select an item programmatically
        //bottomNavigationView.getMenu().getItem(2).setChecked(true);

        DrawerLayout drawer = (DrawerLayout) findViewById(com.weatherapp.R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, com.weatherapp.R.string.navigation_drawer_open, com.weatherapp.R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(com.weatherapp.R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Code for temperature
        double lat1 = 44.7866, lon1 = 20.4489;
        double lat2 = 41.3851, lon2 = 2.1734;
        double lat3 = 59.3275, lon3 = 18.0675;
        double lat4 = 44.8683, lon4 = 13.84806;
        double lat5 = 45.5161, lon5 = -73.6568;
        double lat6 = 45.3502, lon6 = 15.8805;

        String units = "metric";

        String url1 = String.format("http://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&units=%s&appid=%s",
                lat1, lon1, units, APP_ID);

        TextView textView = (TextView) findViewById(R.id.temperature1);
        new GetWeatherTask(textView).execute(url1);

        //kod za temperature minimum
        TextView textWind1 = (TextView) findViewById(R.id.tempMin1);
        new GetMin(textWind1).execute(url1);

        TextView textMax1 = (TextView) findViewById(R.id.tempMax1);
        new GetMax(textMax1).execute(url1);

        String url2 = String.format("http://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&units=%s&appid=%s",
                lat2, lon2, units, APP_ID);

        TextView textView1 = (TextView) findViewById(R.id.temperature2);
        new GetWeatherTask(textView1).execute(url2);

        TextView textWind2 = (TextView) findViewById(R.id.tempMin2);
        new GetMin(textWind2).execute(url2);

        TextView textMax2 = (TextView) findViewById(R.id.tempMax2);
        new GetMax(textMax2).execute(url2);

        String url3 = String.format("http://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&units=%s&appid=%s",
                lat3, lon3, units, APP_ID);

        TextView textView2 = (TextView) findViewById(R.id.temperature3);
        new GetWeatherTask(textView2).execute(url3);

        TextView textWind3 = (TextView) findViewById(R.id.tempMin3);
        new GetMin(textWind3).execute(url3);

        TextView textMax3 = (TextView) findViewById(R.id.tempMax3);
        new GetMax(textMax3).execute(url3);

        String url4 = String.format("http://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&units=%s&appid=%s",
                lat4, lon4, units, APP_ID);

        TextView textView3 = (TextView) findViewById(R.id.temperature4);
        new GetWeatherTask(textView3).execute(url4);

        TextView textWind4 = (TextView) findViewById(R.id.tempMin4);
        new GetMin(textWind4).execute(url4);

        TextView textMax4 = (TextView) findViewById(R.id.tempMax4);
        new GetMax(textMax4).execute(url4);

        String url5 = String.format("http://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&units=%s&appid=%s",
                lat5, lon5, units, APP_ID);

        TextView textView4 = (TextView) findViewById(R.id.temperature5);
        new GetWeatherTask(textView4).execute(url5);

        TextView textWind5 = (TextView) findViewById(R.id.tempMin5);
        new GetMin(textWind5).execute(url5);

        TextView textMax5 = (TextView) findViewById(R.id.tempMax5);
        new GetMax(textMax5).execute(url5);

        String url6 = String.format("http://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&units=%s&appid=%s",
                lat6, lon6, units, APP_ID);

        TextView textView5 = (TextView) findViewById(R.id.temperature6);
        new GetWeatherTask(textView5).execute(url6);

        TextView textWind6 = (TextView) findViewById(R.id.tempMin6);
        new GetMin(textWind6).execute(url6);

        TextView textMax6 = (TextView) findViewById(R.id.tempMax6);
        new GetMax(textMax6).execute(url6);

        temperature();
    }

    public void temperature (){

        //Code for temperature
        double lat1 = 44.7866, lon1 = 20.4489;
        double lat2 = 41.3851, lon2 = 2.1734;
        double lat3 = 59.3275, lon3 = 18.0675;
        double lat4 = 44.8683, lon4 = 13.84806;
        double lat5 = 45.5161, lon5 = -73.6568;
        double lat6 = 45.3502, lon6 = 15.8805;

        String units = "metric";

        String url1 = String.format("http://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&units=%s&appid=%s",
                lat1, lon1, units, APP_ID);

        TextView tempNow1 = (TextView) findViewById(R.id.temperature1);
        new GetWeatherTask(tempNow1).execute(url1);

        TextView textMin1 = (TextView) findViewById(R.id.tempMin1);
        new GetMin(textMin1).execute(url1);

        TextView textMax1 = (TextView) findViewById(R.id.tempMax1);
        new GetMax(textMax1).execute(url1);

        String url2 = String.format("http://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&units=%s&appid=%s",
                lat2, lon2, units, APP_ID);

        TextView tempNow2 = (TextView) findViewById(R.id.temperature2);
        new GetWeatherTask(tempNow2).execute(url2);

        TextView textMin2 = (TextView) findViewById(R.id.tempMin2);
        new GetMin(textMin2).execute(url2);

        TextView textMax2 = (TextView) findViewById(R.id.tempMax2);
        new GetMax(textMax2).execute(url2);

        String url3 = String.format("http://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&units=%s&appid=%s",
                lat3, lon3, units, APP_ID);

        TextView tempNow3 = (TextView) findViewById(R.id.temperature3);
        new GetWeatherTask(tempNow3).execute(url3);

        TextView textMin3 = (TextView) findViewById(R.id.tempMin3);
        new GetMin(textMin3).execute(url3);

        TextView textMax3 = (TextView) findViewById(R.id.tempMax3);
        new GetMax(textMax3).execute(url3);

        String url4 = String.format("http://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&units=%s&appid=%s",
                lat4, lon4, units, APP_ID);

        TextView tempNow4 = (TextView) findViewById(R.id.temperature4);
        new GetWeatherTask(tempNow4).execute(url4);

        TextView textMin4 = (TextView) findViewById(R.id.tempMin4);
        new GetMin(textMin4).execute(url4);

        TextView textMax4 = (TextView) findViewById(R.id.tempMax4);
        new GetMax(textMax4).execute(url4);

        String url5 = String.format("http://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&units=%s&appid=%s",
                lat5, lon5, units, APP_ID);

        TextView tempNow5 = (TextView) findViewById(R.id.temperature5);
        new GetWeatherTask(tempNow5).execute(url5);

        TextView textMin5 = (TextView) findViewById(R.id.tempMin5);
        new GetMin(textMin5).execute(url5);

        TextView textMax5 = (TextView) findViewById(R.id.tempMax5);
        new GetMax(textMax5).execute(url5);

        String url6 = String.format("http://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&units=%s&appid=%s",
                lat6, lon6, units, APP_ID);

        TextView tempNow6 = (TextView) findViewById(R.id.temperature6);
        new GetWeatherTask(tempNow6).execute(url6);

        TextView textMin6 = (TextView) findViewById(R.id.tempMin6);
        new GetMin(textMin6).execute(url6);

        TextView textMax6 = (TextView) findViewById(R.id.tempMax6);
        new GetMax(textMax6).execute(url6);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(com.weatherapp.R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(com.weatherapp.R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.login:

                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(com.weatherapp.R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }

        private class GetWeatherTask extends AsyncTask<String, Void, String> {
            private TextView textView;

            public GetWeatherTask(TextView textView) {
                this.textView = textView;
            }

            @Override
            protected String doInBackground(String... strings) {
                String weather = "UNDEFINED";
                try {
                    URL url = new URL(strings[0]);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
                    StringBuilder builder = new StringBuilder();

                    String inputString;
                    while ((inputString = bufferedReader.readLine()) != null) {
                        builder.append(inputString);
                    }

                    JSONObject topLevel = new JSONObject(builder.toString());
                    JSONObject main = topLevel.getJSONObject("main");
                    weather = String.valueOf(main.getDouble("temp"));

                    urlConnection.disconnect();
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
                return weather;
            }

            @Override
            protected void onPostExecute(String temp) {
                textView.setText(temp + "ºC");
            }
        }


    private class GetMin extends AsyncTask<String, Void, String> {
        private TextView textMin;

        public GetMin(TextView textMin) {
            this.textMin = textMin;
        }

        @Override
        protected String doInBackground(String... strings) {
            String tempMin = "UNDEFINED";
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder builder = new StringBuilder();

                String inputString;
                while ((inputString = bufferedReader.readLine()) != null) {
                    builder.append(inputString);
                }

                JSONObject topLevel = new JSONObject(builder.toString());
                JSONObject main = topLevel.getJSONObject("main");
                tempMin = String.valueOf(main.getDouble("temp_min"));

                urlConnection.disconnect();
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return tempMin;
        }

        @Override
        protected void onPostExecute(String min) {
            textMin.setText(min + "ºC");
        }
    }


    private class GetMax extends AsyncTask<String, Void, String> {
        private TextView textMax;

        public GetMax(TextView textMax) {
            this.textMax = textMax;
        }

        @Override
        protected String doInBackground(String... strings) {
            String tempMax = "UNDEFINED";
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder builder = new StringBuilder();

                String inputString;
                while ((inputString = bufferedReader.readLine()) != null) {
                    builder.append(inputString);
                }

                JSONObject topLevel = new JSONObject(builder.toString());
                JSONObject main = topLevel.getJSONObject("main");
                tempMax = String.valueOf(main.getDouble("temp_max"));

                urlConnection.disconnect();
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return tempMax;
        }

        @Override
        protected void onPostExecute(String max) {
            textMax.setText(max + "ºC");
        }
    }




}


