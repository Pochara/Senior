package com.example.pp.accelerometer;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    TextView textX, textY, textZ, texttotal;
    SensorManager sensorManager;
    Sensor sensor;
    Display mDisplay;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        textX = (TextView) findViewById(R.id.textX);
        textY = (TextView) findViewById(R.id.textY);
        textZ = (TextView) findViewById(R.id.textZ);
        texttotal = (TextView) findViewById(R.id.texttotal);
        Button StartAccelerometer = (Button) findViewById(R.id.BtnAccelerometerStart);
        Button StopAccelerometer = (Button) findViewById(R.id.BtnAccelerometerStop);
        StartAccelerometer.setOnClickListener(StartExercise);
        StopAccelerometer.setOnClickListener(StopExercise);
    }
    View.OnClickListener StartExercise = new View.OnClickListener() {
        public void onClick(View v) {
            sensorManager.registerListener(accelListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    };
    View.OnClickListener StopExercise = new View.OnClickListener() {
        public void onClick(View v) {
            sensorManager.unregisterListener(accelListener);
        }
    };
    public void onResume() {
        super.onResume();
    }

    public void onStop() {
        super.onStop();
    }

    int numexe=0;
    int startpoint=0;
    int firstcommit=1;
    public void count(int x,int y,int z){

        if(firstcommit == 1) {
            Log.v("x",x+"");
            Log.v("y",y+"");
            Log.v("z",z+"");
            firstcommit++;
        }
        if (z == 9 && x == 0) {
            startpoint++;
        }
        if (startpoint > 0 && x == -9 && z == 0) {
            numexe = numexe + 1;
        }
    }

    SensorEventListener accelListener = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int acc) {

        }

        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            textX.setText("X : " + (int)x);
            textY.setText("Y : " + (int)y);
            textZ.setText("Z : " + (int)z);
            texttotal.setText("Total : " + numexe);
            count((int)x,(int)y,(int)z);
        }

    };
}
