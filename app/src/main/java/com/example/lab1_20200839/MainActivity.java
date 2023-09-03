package com.example.lab1_20200839;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView titleTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titleTextView = findViewById(R.id.textAhorcado);
        registerForContextMenu(titleTextView);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);
        getMenuInflater().inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.option_blue) {
            titleTextView.setTextColor(Color.BLUE);
            return true;
        } else if (item.getItemId() == R.id.option_red) {
            titleTextView.setTextColor(Color.RED);
            return true;
        } else if (item.getItemId() == R.id.option_green) {
            titleTextView.setTextColor(Color.GREEN);
            return true;
        } else {
            return super.onContextItemSelected(item);
        }
    }
    public void jugar(View view){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }


}