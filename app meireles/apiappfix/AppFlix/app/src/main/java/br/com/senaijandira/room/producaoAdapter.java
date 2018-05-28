package br.com.senaijandira.room;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class producaoAdapter  extends ArrayAdapter<Producao> {
    public producaoAdapter(Context contexto){
        super(contexto, 0, new ArrayList<Producao>());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_view_item, null);
        }

        //Pegando o item que sera carregado
        Producao item = getItem(position);

        //ViewHolder
        ImageView imgItem = view.findViewById(R.id.imgItem);
        TextView txtEmail = view.findViewById(R.id.txtItemEmail);

        imgItem.setImageBitmap(item);
        txtEmail.setText(item.getEmail());

        return view;
    }
}

