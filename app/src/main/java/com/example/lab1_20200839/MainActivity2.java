package com.example.lab1_20200839;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.Gravity;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity2 extends AppCompatActivity {
    private String[] palabras;
    private int numCorr;
    private Random random;
    private TextView[] letrasView;
    private String palabraActual;
    private LinearLayout wordLayout;
    private TecladoLetras adapter;
    private GridView gridView;
    private int numChars;
    private ImageView[] parts;
    private int sizeParts=6;
    private int parteActual;
    private long startTime;
    private long endTime;
    private long duracion;
    private ArrayList<Long> tiemposPartidas = new ArrayList<>();
    private ArrayList<String> resultadosPartidas = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        palabras=getResources().getStringArray(R.array.palabras);
        wordLayout=findViewById(R.id.palabras);
        gridView=findViewById(R.id.letras);
        random=new Random();
        parts=new ImageView[sizeParts];
        parts[0] = findViewById(R.id.cabeza);
        parts[1] = findViewById(R.id.cuerpo);
        parts[2] = findViewById(R.id.brazoIzq);
        parts[3] = findViewById(R.id.brazoDer);
        parts[4] = findViewById(R.id.piernaIzq);
        parts[5] = findViewById(R.id.piernaDer);


        iniciarJuego();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_stats,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.stats) {
            // Inicia una nueva actividad para mostrar estadísticas
            Intent intent = new Intent(this, Statistics.class);
            intent.putExtra("tiemposPartidas", tiemposPartidas);
            intent.putExtra("resultadosPartidas", resultadosPartidas);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void iniciarJuego(){
        wordLayout.removeAllViews();

        String nuevaPalabra=palabras[random.nextInt(palabras.length)];
        while(nuevaPalabra.equals(palabraActual)) nuevaPalabra=palabras[random.nextInt(palabras.length)];
        palabraActual=nuevaPalabra;

        letrasView=new TextView[palabraActual.length()];
        for(int i=0;i<palabraActual.length();i++){
            letrasView[i]=new TextView(this);
            letrasView[i].setText(""+palabraActual.charAt(i));
            letrasView[i].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            letrasView[i].setGravity(Gravity.CENTER);
            letrasView[i].setTextColor(Color.WHITE);
            letrasView[i].setBackgroundResource(R.drawable.colocar_letra);
            wordLayout.addView(letrasView[i]);
        }
        for (ImageView part : parts) {
            part.setVisibility(View.INVISIBLE);
        }
        adapter=new TecladoLetras(this);
        gridView.setAdapter(adapter);
        numCorr=0;
        parteActual=0;
        numChars=palabraActual.length();
        startTime = System.currentTimeMillis();;
    }
    public void letraPresionada(View view){
        String letra=((TextView) view).getText().toString();
        char letrachar=letra.charAt(0);

        view.setEnabled(false);
        boolean bandera=false;
        for(int i=0;i<palabraActual.length();i++){
            if(palabraActual.charAt(i)==letrachar){
                bandera=true;
                numCorr++;
                letrasView[i].setTextColor(Color.BLACK);
            }
        }
        if(bandera){
            if(numCorr==numChars){
                desabilitarBotones();
                endTime = System.currentTimeMillis();
                duracion = (endTime - startTime)/1000;
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setTitle("Ganó");
                builder.setMessage("Terminó en \n\n" +duracion+"s");
                builder.setPositiveButton("Jugar de nuevo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity2.this.iniciarJuego();

                    }
                });
                builder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity2.this.finish();
                    }
                });
                builder.show();
                tiemposPartidas.add(duracion);
                resultadosPartidas.add("Gano");
            }
        } else if (parteActual<sizeParts) {
            parts[parteActual].setVisibility(View.VISIBLE);
            parteActual++;
        }else {
            endTime = System.currentTimeMillis();
            duracion = (endTime - startTime)/1000;
            desabilitarBotones();
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("Perdiste");
            builder.setMessage("Sigue intentándolo!\n\nLa reespuestanera \n\n" +palabraActual);
            builder.setPositiveButton("Jugar nuevamente", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity2.this.iniciarJuego();

                }
            });
            builder.setPositiveButton("Salir", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity2.this.finish();
                }
            });
            builder.show();
            tiemposPartidas.add(duracion);
            resultadosPartidas.add("Perdio");
        }

    }
    public void desabilitarBotones(){
        for(int i=0;i<gridView.getChildCount();i++){
            gridView.getChildAt(i).setEnabled(false);
        }
    }
    public void onClickJugarDeNuevo(View view) {
        iniciarJuego();
        endTime = System.currentTimeMillis();
        tiemposPartidas.add(duracion);
        resultadosPartidas.add("Cancelo");
    }

}