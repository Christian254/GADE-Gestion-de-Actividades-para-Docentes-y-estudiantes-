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

/**
 * Created by HP on 15/5/2018.
 */

public class ArrayAdapterLH extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<AdapterLH> items;

    public ArrayAdapterLH(Activity activity,ArrayList<AdapterLH> items) {
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

    public void addAll(ArrayList<AdapterLH> p){
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
            v = inf.inflate(R.layout.list_l_h_c,null);
        }
        AdapterLH f = items.get(position);
        TextView texto = (TextView)v.findViewById(R.id.txl_h_c);
        texto.setText(f.getTitle());
        String ids = String.valueOf(f.getId());
        texto.setHint(ids);
        return v;
    }

}


