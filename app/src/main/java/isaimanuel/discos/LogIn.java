package isaimanuel.discos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LogIn extends AppCompatActivity {
    public EditText user;
    public EditText pass;
    String nombre;
    String apellido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user=(EditText)findViewById(R.id.user);
        pass=(EditText)findViewById(R.id.password);

    }
    public void submit(View view){

        String url  = "http://192.168.0.11/login.php?user=" + user.getText().toString() + "&pass='"+pass.getText().toString()+"'";

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
                                    nombre=(String)response.getString("nombre");
                                    apellido=(String)response.getString("apellido");
                                    if(nombre!=null){
                                        Intent mas=new Intent(LogIn.this,Nota.class);
                                        mas.putExtra("nombre",nombre);
                                        mas.putExtra("apellido",apellido);
                                        mas.putExtra("clave",user.getText().toString());
                                        nombre=null;
                                        startActivity(mas);}
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) 	{
                        Toast.makeText(LogIn.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue x = Volley.newRequestQueue(this);

        x.add(peticion);


    }
}
