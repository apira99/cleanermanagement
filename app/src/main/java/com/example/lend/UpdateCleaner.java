package com.example.lend;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateCleaner extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_cleaner);

        // Get the book ID from the intent
        String cleanerId = getIntent().getStringExtra("cleanerId");

        // Set the "Update Book" button click listener
        findViewById(R.id.buttonupdateCleaner).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the updated book details from the user
                EditText editTextCleanerId = findViewById(R.id.editTextCleanerId1);
                EditText editTextCleanerName = findViewById(R.id.editTextName1);
                EditText editTextCleanerPhonenumber = findViewById(R.id.editTextPhonenumber1);

                String updatedCleanerId = editTextCleanerId.getText().toString();
                String updatedName = editTextCleanerName.getText().toString();
                String updatedPhonenumber = editTextCleanerPhonenumber.getText().toString();

                // Update the book in the database
                DBHandler dbHandler = new DBHandler(UpdateCleaner.this);
                dbHandler.UpdateCleaner(cleanerId, updatedName, updatedPhonenumber);

                // Show a success message to the user
                Toast.makeText(UpdateCleaner.this, "Updated book: " + updatedName + " by " + updatedPhonenumber, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateCleaner.this, Book.class);
                startActivity(intent);
            }
        });
    }
}