package com.example.lend;

import static androidx.core.content.ContextCompat.startActivity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;

public class HomePage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        Button cmanageButton = findViewById(R.id.button1);

        cmanageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomePage.this, "Button clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HomePage.this, Book.class);
                startActivity(intent);
            }
        });

        Button csearchButton = findViewById(R.id.button2);

        csearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomePage.this, "Button clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HomePage.this, ViewCleaner.class);
                startActivity(intent);
            }
        });



    }
}
