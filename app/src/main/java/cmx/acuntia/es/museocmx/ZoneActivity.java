package cmx.acuntia.es.museocmx;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class ZoneActivity extends AppCompatActivity {

    TextView textUbic;
    String ubicacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textUbic = (TextView) findViewById(R.id.textView);

        ubicacion = getIntent().getStringExtra("ubicacion");
        textUbic.setText(ubicacion);

    }

}
