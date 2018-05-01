package sv.edu.ues.fia.gade.adapter;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.model.Usuario;

/**
 * Created by HP on 29/4/2018.
 */

public class UsersAdapter extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Adaptador> items;

    public UsersAdapter(Activity activity,ArrayList<Adaptador> items) {
        this.activity=activity;
        this.items=items;
    }


    @Override
    public int getCount() {
        return items.size();
    }
    public void clear(){
        items.clear();
    }

    public void addAll(ArrayList<Adaptador> p){
        for(int i=0;i<p.size();i++){
            items.add(p.get(i));
        }
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(convertView==null){
            LayoutInflater inf = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.list_row_users,null);
        }
        Adaptador f = items.get(position);
        TextView texto = (TextView)v.findViewById(R.id.tx);
        texto.setText(f.getUsuario().getUsername());
        String ids = String.valueOf(f.getUsuario().getTipo());
        texto.setHint(ids);
        TextView saldo = (TextView)v.findViewById(R.id.tx2);
        saldo.setText(String.valueOf(f.getAcces().getIdOpcion()));

        TextView texto3 = (TextView)v.findViewById(R.id.tx3);
        texto3.setText(f.getOp().getNombreCRUD());
        return v;
    }

}

