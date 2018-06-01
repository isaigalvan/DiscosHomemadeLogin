package isaimanuel.discos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class DISCOS extends AppCompatActivity {
    EditText usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discos);
        usuario=(EditText)findViewById(R.id.user);
    }
}
