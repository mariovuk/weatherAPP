package com.weatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;


public class ForecastOptions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forecast_options);


        ImageButton chooseButton = (ImageButton) findViewById(R.id.chooseButton);

        chooseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ForecastOptions.this, "test", Toast.LENGTH_SHORT).show();
            }
        });
    }

}




