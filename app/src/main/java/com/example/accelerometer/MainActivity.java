package com.example.accelerometer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    TextView x,y,z, color;
    int colorShade = 0;
    WindowManager.LayoutParams colorParams;
    RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor accelerometerSensor  = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometerSensor, sensorManager.SENSOR_DELAY_NORMAL);

        x = findViewById(R.id.xaxis_id);
        y = findViewById(R.id.yaxis_id);
        z = findViewById(R.id.zaxis_id);
        color = findViewById(R.id.color_id);
        layout = findViewById(R.id.layout);



        colorParams = getWindow().getAttributes();
        color.setText(colorShade + "");


    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        x.setText(sensorEvent.values[0] + "");
        y.setText(sensorEvent.values[1] + "");
        z.setText(sensorEvent.values[2] + "");

        //Change conditions here to make sure the code shifts shades when the phone is tilted
        //Use the values above and increment each of the RGB values using the colorShade Variable to change the shade of the background
        //make sure it is darker when the phone is more vertical

        if(colorShade<255){
            colorShade += 1;

            layout.setBackgroundColor(Color.argb(colorShade,
                    43, 34, 15));

        }
        else{

        }

        colorParams.screenBrightness = colorShade;
        getWindow().setAttributes(colorParams);
        color.setText(colorShade + "");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}