package com.example.taamefl2.app_metaldetektor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager metalSensorManager;
    private Sensor magneticField;
    private ProgressBar fortschritt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // UI part
        Button button = findViewById(R.id.showGraph);
        fortschritt = findViewById(R.id.show_progress);


        // Sensor part
        metalSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        magneticField = metalSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Detect magnetic field in environment:

            }
        });
    }

    protected void onResume() {
        super.onResume();
        metalSensorManager.registerListener(this, magneticField, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        metalSensorManager.unregisterListener(this);
    }

    public void onSensorChanged(SensorEvent event) {
        float[] mag = event.values;
        double betrag = Math.sqrt(mag[0] * mag[0] + mag[1] * mag[1] + mag[2] * mag[2]);
        fortschritt.setProgress((int)betrag);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
