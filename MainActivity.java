package com.example.dcp.sensorvalueplot;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements SensorEventListener{

    private SensorManager lightSensor,pressureSensor,accelSensor,humiditySensor;

    TextView lightText,pressureText,tempText,humidityText;

    Button act1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lightSensor = (SensorManager) getSystemService(SENSOR_SERVICE);
        lightSensor.registerListener(this,lightSensor.getDefaultSensor(Sensor.TYPE_LIGHT),SensorManager.SENSOR_DELAY_NORMAL);
        pressureSensor = (SensorManager) getSystemService(SENSOR_SERVICE);
        pressureSensor.registerListener(this,pressureSensor.getDefaultSensor(Sensor.TYPE_PRESSURE),SensorManager.SENSOR_DELAY_NORMAL);
        accelSensor = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelSensor.registerListener(this,accelSensor.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);
        humiditySensor = (SensorManager) getSystemService(SENSOR_SERVICE);
        humiditySensor.registerListener(this,humiditySensor.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY),SensorManager.SENSOR_DELAY_NORMAL);

        lightText = (TextView) findViewById(R.id.light);
        pressureText =(TextView) findViewById(R.id.pressure);
        tempText =(TextView) findViewById(R.id.accelro);
        humidityText =(TextView) findViewById(R.id.humidity);

        act1 =(Button)findViewById(R.id.button);

        act1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                showPlots();
            }
        });

    }
    private void showPlots(){
        Intent intent1 =new Intent(this,PlotActivity.class);
        startActivity(intent1);
    }
    @Override
    public void onAccuracyChanged (Sensor arg0,int arg1){

    }

    @Override
    public void onSensorChanged (SensorEvent event){

        if(event.sensor.getType()==Sensor.TYPE_LIGHT){
            float lightIntensity =  event.values[0];
            lightText.setText(Float.toString(lightIntensity)+"lx");
        }
        if(event.sensor.getType()==Sensor.TYPE_PRESSURE){
            float pressure =  event.values[0];
            pressureText.setText(Float.toString(pressure)+"mbar");
        }
        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            float xVal = event.values[0];
            float yVal = event.values[1];
            float zVal = event.values[2];

            double xyzVal=Math.sqrt(xVal*xVal+yVal*yVal+zVal*zVal);

            tempText.setText(Double.toString(xyzVal)+"m/s^2");
        }
        if(event.sensor.getType()==Sensor.TYPE_RELATIVE_HUMIDITY){
            float humidity =  event.values[0];
            humidityText.setText(Float.toString(humidity)+"%");
        }
    }
}
