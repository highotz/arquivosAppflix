package br.com.senaijandira.apicontatos;

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

import br.com.senaijandira.room.Producao;
import br.com.senaijandira.room.R;

/**
 * Created by 17170095 on 02/05/2018.
 */

public class ProducaoAdapter extends ArrayAdapter<Producao> {
    public ProducaoAdapter(Context ctx){
        super(ctx, 0, new ArrayList<Producao>());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.list_view_item,null);
        }
        //pegando o item que será carregado
        Producao item = getItem(position);

        //ViewHolder é uma atualização disso
        ImageView img_item = v.findViewById(R.id.img_item);
        TextView txt_titulo_item = v.findViewById(R.id.txt_titulo_item);

        //atualizando a UI
        //img_item.setImageBitmap(item.ge);
        //txt_email.setText(item.getEmail());

        return v;
    }
}
