package com.example.todotask;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Objects;

public class NoteEditor extends AppCompatActivity {

    int noteID;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_editor);
        Objects.requireNonNull(getSupportActionBar()).hide();

        //save
        Button save = findViewById(R.id.saveTask);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NoteEditor.this, MainActivity.class));
            }
        });
        //Save

        EditText editText = findViewById(R.id.editTask);

        Intent intent = getIntent();
        noteID = intent.getIntExtra("noteID", -1);

        if (noteID != -1){
            editText.setText(MainActivity.task.get(noteID));
        }else{
            MainActivity.task.add("");
            noteID = MainActivity.task.size() - 1;
            MainActivity.arrayAdapter.notifyDataSetChanged();
        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                MainActivity.task.set(noteID, String.valueOf(charSequence));
                MainActivity.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(".todotask", Context.MODE_PRIVATE);

                HashSet<String> set = new HashSet<>(MainActivity.task);

                sharedPreferences.edit().putStringSet("task", set).apply();
            }
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
