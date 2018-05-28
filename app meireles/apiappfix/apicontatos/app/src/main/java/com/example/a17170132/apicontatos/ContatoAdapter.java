package com.example.a17170132.apicontatos;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ContatoAdapter extends ArrayAdapter<Contato>{

    public ContatoAdapter(Context contexto){
        super(contexto, 0, new ArrayList<Contato>());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_view_item, null);
        }

        //Pegando o item que sera carregado
        Contato item = getItem(position);

        //ViewHolder
        TextView txtNome = view.findViewById(R.id.txtItemNome);
        TextView txtEmail = view.findViewById(R.id.txtItemEmail);

        txtNome.setText(item.getNome());
        txtEmail.setText(item.getEmail());

        return view;
    }
}
