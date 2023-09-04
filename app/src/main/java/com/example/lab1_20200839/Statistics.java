package com.example.lab1_20200839;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class Statistics extends AppCompatActivity {
    private ArrayList<Long> tiemposPartidas;
    private ArrayList<String> resultadosPartidas;
    private TextView textStats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        textStats = findViewById(R.id.textStats);
        Intent intent = getIntent();
        tiemposPartidas = (ArrayList<Long>) intent.getSerializableExtra("tiemposPartidas");
        resultadosPartidas = (ArrayList<String>) intent.getSerializableExtra("resultadosPartidas");

        if (tiemposPartidas != null && resultadosPartidas != null) {
            mostrarResultados(tiemposPartidas, resultadosPartidas);
        } else {
            textStats.setText("No se han recibido datos de estadísticas.");
        }
    }
    private void mostrarResultados(ArrayList<Long> tiemposPartidas,ArrayList<String> resultadosPartidas) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tiemposPartidas.size(); i++) {
            stringBuilder.append("Juego ").append(i + 1).append(": ");
            String resultado = resultadosPartidas.get(i);
            if (resultado.equals("Gano") || resultado.equals("Perdio")) {
                stringBuilder.append(resultado).append(" Termino en ").append(tiemposPartidas.get(i)).append("s");
            } else {
                stringBuilder.append(resultado);
            }
            stringBuilder.append("\n");
        }

        // Establece el texto en el TextView
        textStats.setText(stringBuilder.toString());
    }
    public void iniciarJuego(View view) {
        // Llama al método iniciarJuego de MainActivity2
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
        finish(); // Finaliza la actividad actual para que puedas iniciar un nuevo juego
    }




}