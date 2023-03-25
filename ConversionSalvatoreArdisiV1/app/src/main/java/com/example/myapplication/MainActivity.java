package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText milesInput = findViewById(R.id.miles_input);
        TextView milesOutput = findViewById(R.id.miles_output);
        EditText kiloInput = findViewById(R.id.ki_input);
        TextView kiloOutput = findViewById(R.id.km_output);

        Button milesButton = findViewById(R.id.miles_button);
        Button kiloButton = findViewById(R.id.kilometer_button);

        milesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String milesText = milesInput.getText().toString();
                double miles = Double.parseDouble(milesText);
                double kilo = miles * 1.60934;
                milesOutput.setText(Double.toString(kilo));
            }
        });
        kiloButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kiloText = kiloInput.getText().toString();
                double kilo = Double.parseDouble(kiloText);
                double miles = kilo / 1.60934;
                kiloOutput.setText(Double.toString(miles));
            }
        });
    }
}