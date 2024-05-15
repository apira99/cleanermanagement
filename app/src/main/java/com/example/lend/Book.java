package com.example.lend;


import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ImageButton;


public class Book extends AppCompatActivity {

    private Button btnAddCleaner, btnUpdateCleaner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaner);

        btnAddCleaner = findViewById(R.id.btnAddCleaner);
        btnUpdateCleaner= findViewById(R.id.btnUpdateCleaner);


        ImageButton imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Book.this, "Back to home", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Book.this, HomePage.class);
                startActivity(intent);
            }
        });

        // Set click listeners for each button
        btnAddCleaner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Book.this, "Add Cleaner clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Book.this, AddCleaner.class);
                startActivity(intent);
            }
        });



        btnUpdateCleaner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Book.this, "Update Cleaner clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Book.this, UpdateCleaner.class);
                startActivity(intent);
            }
        });


    }

}
