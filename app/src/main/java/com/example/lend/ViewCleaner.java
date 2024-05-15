package com.example.lend;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ViewCleaner extends AppCompatActivity implements SearchView.OnQueryTextListener {

    RecyclerView recyclerView;
    FloatingActionButton search_button;
    ImageButton back_button;
    ImageView empty_imageview;
    TextView no_data;

    DBHandler myDB;
    ArrayList<String> cleaner_id, cleaner_name, cleaner_phonenumber;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cleaner);

        recyclerView = findViewById(R.id.recyclerView);
        search_button = findViewById(R.id.search_button);
        back_button=findViewById(R.id.imagebtn);
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);

        myDB = new DBHandler(ViewCleaner.this);
        cleaner_id = new ArrayList<>();
        cleaner_name = new ArrayList<>();
        cleaner_phonenumber = new ArrayList<>();

        // Initialize search button
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSearchDialog();
            }
        });
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSearchDialog();
            }
        });

        storeDataInArrays();

        customAdapter = new CustomAdapter(ViewCleaner.this,this, cleaner_id, cleaner_name,cleaner_phonenumber);

        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ViewCleaner.this));
    }

    // Method to display search dialog
    private void showSearchDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Search Cleaner");

        // Create search view
        final SearchView searchView = new SearchView(this);
        searchView.setQueryHint("Search by name...");
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(this);

        builder.setView(searchView);

        builder.setPositiveButton("Search", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Perform search operation
                String query = searchView.getQuery().toString().trim();
                filterCleaners(query);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void onClick(View v) {
        // Navigate back to the LoginActivity
        Intent intent = new Intent(ViewCleaner.this, Book.class);
        startActivity(intent);
        // Finish the current activity to prevent going back to it when pressing the back button
        finish();
    }
    // Method to filter cleaners based on search query
    private void filterCleaners(String query) {
        ArrayList<String> filteredIds = new ArrayList<>();
        ArrayList<String> filteredNames = new ArrayList<>();
        ArrayList<String> filteredPhoneNumbers = new ArrayList<>();

        for (int i = 0; i < cleaner_name.size(); i++) {
            if (cleaner_name.get(i).toLowerCase().contains(query.toLowerCase())) {
                filteredIds.add(cleaner_id.get(i));
                filteredNames.add(cleaner_name.get(i));
                filteredPhoneNumbers.add(cleaner_phonenumber.get(i));
            }
        }

        customAdapter = new CustomAdapter(ViewCleaner.this, ViewCleaner.this, filteredIds, filteredNames, filteredPhoneNumbers);
        recyclerView.setAdapter(customAdapter);
        customAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        // Not used
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        // Not used
        return false;
    }

    void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        } else {
            while (cursor.moveToNext()) {
                cleaner_id.add(cursor.getString(0));
                cleaner_name.add(cursor.getString(1));
                cleaner_phonenumber.add(cursor.getString(2));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }
}
