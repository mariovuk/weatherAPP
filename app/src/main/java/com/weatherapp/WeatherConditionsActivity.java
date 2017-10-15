package com.weatherapp;

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
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by mariovuksic on 2017-10-15.
 */

public class WeatherConditionsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TextView textView1;
    DrawerLayout dLayout;

    private static final String APP_ID = "b5362c203397ce801d354458c2262485";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_other);
        Toolbar toolbar = (Toolbar) findViewById(com.weatherapp.R.id.toolbar);
        setSupportActionBar(toolbar);
        textView1 = (TextView) findViewById(R.id.textView1);// text to show addresses


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

        //kod za wind
        TextView textWind1 = (TextView) findViewById(R.id.wind1);
        new WeatherConditionsActivity.GetWind(textWind1).execute(url1);

        String url2 = String.format("http://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&units=%s&appid=%s",
                lat2, lon2, units, APP_ID);

        TextView textWind2 = (TextView) findViewById(R.id.wind2);
        new WeatherConditionsActivity.GetWind(textWind2).execute(url2);

        String url3 = String.format("http://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&units=%s&appid=%s",
                lat3, lon3, units, APP_ID);

        TextView textWind3 = (TextView) findViewById(R.id.wind3);
        new WeatherConditionsActivity.GetWind(textWind3).execute(url3);

        String url4 = String.format("http://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&units=%s&appid=%s",
                lat4, lon4, units, APP_ID);

        TextView textWind4 = (TextView) findViewById(R.id.wind4);
        new WeatherConditionsActivity.GetWind(textWind4).execute(url4);

        String url5 = String.format("http://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&units=%s&appid=%s",
                lat5, lon5, units, APP_ID);

        TextView textWind5 = (TextView) findViewById(R.id.wind5);
        new WeatherConditionsActivity.GetWind(textWind5).execute(url5);

        String url6 = String.format("http://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&units=%s&appid=%s",
                lat6, lon6, units, APP_ID);

        TextView textWind6 = (TextView) findViewById(R.id.wind6);
        new WeatherConditionsActivity.GetWind(textWind6).execute(url6);
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



    private class GetWind extends AsyncTask<String, Void, String> {
        private TextView textView;

        public GetWind(TextView textView) {
            this.textView = textView;
        }

        @Override
        protected String doInBackground(String... strings) {
            String windSpeed = "UNDEFINED";
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
                JSONObject wind = topLevel.getJSONObject("wind");
                windSpeed = String.valueOf(wind.getDouble("speed"));

                urlConnection.disconnect();
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return windSpeed;
        }

        @Override
        protected void onPostExecute(String speed) {
            textView.setText(speed + "m/s");
        }
    }

}
