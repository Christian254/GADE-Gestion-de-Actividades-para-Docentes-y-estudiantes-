package sv.edu.ues.fia.gade.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.clases.Local;
import sv.edu.ues.fia.gade.model.Usuario;

/**
 * Created by HP on 13/5/2018.
 */

public class AdapterLocal extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Local> items;

    public AdapterLocal(Activity activity,ArrayList<Local> items) {
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

    public void addAll(ArrayList<Local> p){
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
            v = inf.inflate(R.layout.list_locales,null);
        }
        Local f = items.get(position);
        TextView texto = (TextView)v.findViewById(R.id.txlocal2);
        texto.setText(f.getNombreLocal());
        String ids = String.valueOf(f.getIdLocal());
        texto.setHint(ids);
        TextView texto2 = (TextView)v.findViewById(R.id.txlocal3);
        texto2.setText("Cupo: "+String.valueOf(f.getTamanoLocal()));
        ImageView imageView = (ImageView)v.findViewById(R.id.imglocale);
        imageView.setImageResource(R.drawable.local);
        return v;
    }

}

