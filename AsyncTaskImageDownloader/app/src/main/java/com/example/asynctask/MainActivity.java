package com.example.asynctask;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.view_image);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageDownloader imageDownloader = new ImageDownloader(MainActivity.this);
                imageDownloader.execute("https://a.espncdn.com/combiner/i?img=/i/teamlogos/ncaa/500/333.png");
            }
        });
    }
}