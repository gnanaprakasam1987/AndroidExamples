package com.example.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gnanaprakasamd.androidexamples.R;

import java.util.List;

public class SensorActivity extends AppCompatActivity {
    private Sensor sensor;
    SensorManager sm = null;
    TextView textView1 = null;
    List list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        /* Get a SensorManager instance */
        sm = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        textView1 = (TextView)findViewById(R.id.textView);

        list = sm.getSensorList(Sensor.TYPE_GYROSCOPE);
        if(list.size()>0){
            sm.registerListener(sel, (Sensor) list.get(0), SensorManager.SENSOR_DELAY_NORMAL);
        }else{
            Toast.makeText(getBaseContext(), "Error: No Accelerometer.", Toast.LENGTH_LONG).show();
        }
    }

    SensorEventListener sel = new SensorEventListener(){
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
        public void onSensorChanged(SensorEvent event) {
            float[] values = event.values;
            textView1.setText("x: "+values[0]+"\ny: "+values[1]+"\nz: "+values[2]);
        }
    };

    @Override
    protected void onStop() {
        if(list.size()>0){
            sm.unregisterListener(sel);
        }
        super.onStop();
    }
}
