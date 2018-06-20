package isaimanuel.discos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter{
    @Override
    public int getCount() {
        return arreglo.size();
    }

    @Override
    public Object getItem(int i) {
        return arreglo.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflate;
        inflate=LayoutInflater.from(context);
        View v=inflate.inflate(R.layout.elemento,null);
        TextView nombre=(TextView)v.findViewById(R.id.nombre);
        nombre.setText(arreglo.get(i).nombre);
        TextView clave=(TextView)v.findViewById(R.id.clave);
        clave.setText(arreglo.get(i).clave);
        TextView cantidad=(TextView)v.findViewById(R.id.cantidad);
        cantidad.setText(arreglo.get(i).cantidad);
        TextView precio=(TextView)v.findViewById(R.id.precio);
        precio.setText(arreglo.get(i).precio);
        TextView subtotal=(TextView)v.findViewById(R.id.subtotal);
        subtotal.setText(arreglo.get(i).subtotal);

        return v;
    }

    public ArrayList<Elemento> arreglo=new ArrayList<>();
    public Context context;
}
