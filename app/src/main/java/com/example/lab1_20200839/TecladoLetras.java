package com.example.lab1_20200839;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

public class TecladoLetras extends BaseAdapter {
    private String[] letras;
    private LayoutInflater letrasInf;
    public TecladoLetras(Context context){
        letras=new String[26];
        for(int i=0;i<letras.length;i++){
            letras[i]=""+(char)(i+'A');
        }
        letrasInf=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return letras.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button btnLetra;
        if(convertView==null){
            btnLetra = (Button) letrasInf.inflate(R.layout.letras,parent,false);
        } else {
            btnLetra=(Button) convertView;
        }
        btnLetra.setText(letras[position]);
        return btnLetra;
    }
}
