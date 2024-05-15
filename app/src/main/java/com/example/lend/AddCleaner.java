package com.example.lend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCleaner extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cleaner);

        // Set the "Add Book" button click listener
        findViewById(R.id.btnAddCleaner).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the cleaner ID, name, and phone number from the user
                EditText editTextCleanerId = findViewById(R.id.editTextCleanerId);
                EditText editTextCleanerName = findViewById(R.id.editTextCleanerName);
                EditText editTextCleanerPhonenumber = findViewById(R.id.editTextPhonenumber);

                String cleanerId = editTextCleanerId.getText().toString();
                String name = editTextCleanerName.getText().toString();
                String phonenumber= editTextCleanerPhonenumber.getText().toString();

                // Add the cleaner to the database
                DBHandler dbHandler = new DBHandler(AddCleaner.this);
                dbHandler.addcleaner(cleanerId, name, phonenumber);

                // Show a success message to the user
                Toast.makeText(AddCleaner.this, "Added cleaner: " + name + " with " + phonenumber, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddCleaner.this, Book.class);
                startActivity(intent);
            }
        });


    }
}
