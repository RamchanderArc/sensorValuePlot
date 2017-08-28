package com.example.dcp.sensorvalueplot;

import android.content.Intent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;

/**
 * Created by DCP on 8/28/2017.
 */

public class PlotActivity extends AppCompatActivity  {

    Button act2;

    LineChart lightChart,pressureChart,accelChart,humidityChart;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        act2 = (Button)findViewById(R.id.button2);
        act2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                showValues();
            }
        });

    }
    private void showValues(){
        Intent intent =new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
