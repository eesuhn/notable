package com.example.todotask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    static ArrayList<String> task = new ArrayList<>();
    static ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
        //menu
        FloatingActionButton menu = findViewById(R.id.menu);
        menu.setOnClickListener(view -> {
            Context wrapper = new ContextThemeWrapper(MainActivity.this, R.style.popupMenu);
            PopupMenu popupMenu = new PopupMenu(wrapper, menu);
            popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                if (menuItem.getTitle() == getString(R.string.newTask)){
                    Intent intent = new Intent(getApplicationContext(), NoteEditor.class);
                    startActivity(intent);
                }
                if (menuItem.getTitle() == getString(R.string.aboutUs)){
                    Toast.makeText(MainActivity.this, "Working in Progress..", Toast.LENGTH_SHORT).show(); //about us
                }
                return true;
            });
            popupMenu.show();
        });
        //Menu
        //new task
        FloatingActionButton newTask = findViewById(R.id.newTask);
        newTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NoteEditor.class));
            }
        });
        //New task
        ListView listView = findViewById(R.id.listView);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(".todotask", Context.MODE_PRIVATE);

        HashSet<String> set = (HashSet<String>) sharedPreferences.getStringSet("task", null);

        if (set == null){
            task.add(getString(R.string.start));
        }else{
            task = new ArrayList<>(set);
        }
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, task);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(getApplicationContext(), NoteEditor.class);
            intent.putExtra("noteID", i);
            startActivity(intent);
        });
    }
}