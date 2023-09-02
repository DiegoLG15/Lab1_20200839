package com.example.lab1_20200839;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerForContextMenu((TextView) findViewById(R.id.textBienvenido));
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);
        getMenuInflater().inflate(R.menu.menu_context, menu);
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        // Manejar la selección de color del menú contextual
        switch (item.getItemId()) {
            case R.id.menu_blue:
                title.setTextColor(getResources().getColor(R.color.blue));
                return true;
            case R.id.menu_green:
                titleTextView.setTextColor(getResources().getColor(R.color.green));
                return true;
            case R.id.menu_red:
                titleTextView.setTextColor(getResources().getColor(R.color.red));
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

}