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
    protected ArrayList<Usuario> items;

    public UsersAdapter(Activity activity,ArrayList<Usuario> items) {
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

    public void addAll(ArrayList<Usuario> p){
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
        Usuario f = items.get(position);
        TextView texto = (TextView)v.findViewById(R.id.tx);
        texto.setText(f.getUsername());
        String ids = String.valueOf(f.getUsername());
        texto.setHint(ids);
        TextView texto2 = (TextView)v.findViewById(R.id.tx2);
        texto2.setText(String.valueOf(f.getTipo()));
        String ti=null;
        if(f.getTipo()==2){
            ti="Administrador";
        }else{
            ti="Usuario normal";
        }

        TextView texto3 = (TextView)v.findViewById(R.id.tx3);
        texto3.setText(ti);
        return v;
    }

}

