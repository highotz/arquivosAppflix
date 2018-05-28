package br.com.senaijandira.meuscontatos;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sn1041520 on 21/02/2018.
 */

public class ContatoAdapter extends ArrayAdapter<Contato> {


    public ContatoAdapter(Context ctx, ArrayList<Contato> lst){
        super(ctx, 0, lst);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,
                        @NonNull ViewGroup parent) {

        View v = convertView;

        if (v == null){

            v = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_view_item, null);
        }

        //pegar o contato que esta sendo montado
        Contato item = getItem(position);

        ImageView img_item = v.findViewById(R.id.img_item_contato);
        TextView txt_item_nome = v.findViewById(R.id.txt_item_nome);
        TextView txt_item_telefone = v.findViewById(R.id.txt_item_telefone);


        txt_item_nome.setText( item.getNome());
        txt_item_telefone.setText(item.getTelefone());

        if (item.getFoto() != null){
            //atualizando a foto se ela não for nula
            img_item.setImageBitmap(item.getFoto());
        }else{

            //Definir a foto padrão caso não tenha img cadastrada
            Drawable img_padrao = getContext()
                    .getDrawable(android.R.drawable.ic_menu_camera);

            img_item.setImageDrawable(img_padrao);
        }


        return v;
    }
}
