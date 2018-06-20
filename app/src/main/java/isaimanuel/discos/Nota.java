package isaimanuel.discos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class Nota extends AppCompatActivity {
    String claveEmp;
    TextView nombre;
    TextView fecha;
    TextView cant;
    EditText producto;
    ListView list;
    Adaptador adaptador;
    Elemento añadir;
    int cantidad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota);
        String date=""+Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"/"+Calendar.getInstance().get(Calendar.MONTH)+"/"+Calendar.getInstance().get(Calendar.YEAR);
        nombre=(TextView)findViewById(R.id.TVempleado);
        fecha=(TextView)findViewById(R.id.TVfecha);
        Intent mas=getIntent();
        nombre.setText(mas.getStringExtra("nombre")+mas.getStringExtra("apellido"));
        fecha.setText(date);
        claveEmp=mas.getStringExtra("clave");
        cant = (TextView)findViewById(R.id.TVcantidad);
        producto=(EditText)findViewById(R.id.ETclave);
        list=(ListView)findViewById(R.id.lista);
        adaptador=new Adaptador();
        adaptador.context=this;
        list.setAdapter(adaptador);
        list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        adaptador.arreglo.remove(i);
                        adaptador.notifyDataSetChanged();
                    }
                }
        );


    }
    public void cantmas(View view){
        cantidad=Integer.parseInt(cant.getText().toString());
        cantidad++;
        cant.setText(""+cantidad);
    }
    public void cantmenos(View view){
        cantidad=Integer.parseInt(cant.getText().toString());
        if(cantidad >0)
        cantidad--;
        cant.setText(""+cantidad);
    }
    public void add(View view){
        añadir=new Elemento();
        añadir.clave=producto.getText().toString();
        añadir.cantidad=cant.getText().toString();
        String url  = "http://192.168.0.11/infoDisco.php?disco=" + añadir.clave;

        JsonObjectRequest peticion = new JsonObjectRequest
                (

                        Request.Method.GET,
                        url,
                        null,
                        new Response.Listener<JSONObject>()
                        {
                            @Override
                            public void onResponse(JSONObject response)
                            {
                                try {
                                    añadir.nombre=response.getString("nombre");
                                    añadir.precio=response.getString("precio_venta");
                                    añadir.subtotal=""+Integer.parseInt(añadir.cantidad)*Integer.parseInt(añadir.precio);
                                    adaptador.arreglo.add(añadir);
                                    adaptador.notifyDataSetChanged();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) 	{
                        Toast.makeText(Nota.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue x = Volley.newRequestQueue(this);

        x.add(peticion);
      /*  if(añadir.precio==null)
            añadir.precio=""+1;
        añadir.subtotal=""+Integer.parseInt(añadir.cantidad)*Integer.parseInt(añadir.precio);
        adaptador.arreglo.add(añadir);
        adaptador.notifyDataSetChanged();
    */}
}
