package sv.edu.ues.fia.gade.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import sv.edu.ues.fia.gade.R;
import sv.edu.ues.fia.gade.model.Escuela;

/**
 * Created by HP on 9/5/2018.
 */

public class SpinnerAdminAdapter extends ArrayAdapter<Escuela> {
    private Context context;

    List<Escuela> datos = null;

    public SpinnerAdminAdapter(Context context, List<Escuela> datos)
    {
        //se debe indicar el layout para el item que seleccionado (el que se muestra sobre el botón del botón)
        super(context, R.layout.spinner_selected_item, datos);
        this.context = context;
        this.datos = datos;
    }

    //este método establece el elemento seleccionado sobre el botón del spinner
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            convertView = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.spinner_selected_item,null);
        }
        ((TextView) convertView.findViewById(R.id.texto)).setText(datos.get(position).getNombreEscuela());
        ((TextView) convertView.findViewById(R.id.texto)).setHint(String.valueOf(datos.get(position).getIdentificadorEscuela()));
        ((ImageView) convertView.findViewById(R.id.icono)).setBackgroundResource(R.drawable.admin);

        return convertView;
    }

    //gestiona la lista usando el View Holder Pattern. Equivale a la típica implementación del getView
    //de un Adapter de un ListView ordinario
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent)
    {
        View row = convertView;
        if (row == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.spinner_list_item, parent, false);
        }

        if (row.getTag() == null)
        {
            SpinnerAdminAdapter.EscuelaHolder escuelaHolder = new SpinnerAdminAdapter.EscuelaHolder();
            escuelaHolder.setIcono((ImageView) row.findViewById(R.id.icono));
            escuelaHolder.setTextView((TextView) row.findViewById(R.id.texto));
            row.setTag(escuelaHolder);
        }

        //rellenamos el layout con los datos de la fila que se está procesando
        Escuela escuela = datos.get(position);
        ((EscuelaHolder) row.getTag()).getIcono().setImageResource(R.drawable.escuela);
        ((EscuelaHolder) row.getTag()).getTextView().setText(escuela.getNombreEscuela());
        ((EscuelaHolder) row.getTag()).getTextView().setHint(escuela.getNombreEscuela());

        return row;
    }

    /**
     * Holder para el Adapter del Spinner
     * @author danielme.com
     *
     */
    private static class EscuelaHolder
    {

        private ImageView icono;

        private TextView textView;

        public ImageView getIcono()
        {
            return icono;
        }

        public void setIcono(ImageView icono)
        {
            this.icono = icono;
        }

        public TextView getTextView()
        {
            return textView;
        }

        public void setTextView(TextView textView)
        {
            this.textView = textView;
        }

    }
}