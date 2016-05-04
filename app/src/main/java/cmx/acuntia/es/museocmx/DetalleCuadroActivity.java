package cmx.acuntia.es.museocmx;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleCuadroActivity extends AppCompatActivity {

    String cuadro;
    int imagen;
    ImageView imgCuadro;
    TextView txtCuadro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_cuadro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            String nameCuadro = getIntent().getStringExtra("nombreCuadro");
            toolbar.setTitle(nameCuadro);
        }
        setSupportActionBar(toolbar);
        if (toolbar != null) {
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24px);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }

        txtCuadro = (TextView) findViewById(R.id.textView2);
        imgCuadro = (ImageView) findViewById(R.id.imageView2);

        cuadro = getIntent().getStringExtra("cuadroDetail");
        imagen = getIntent().getIntExtra("imagen",0);

        txtCuadro.setText(cuadro);
        imgCuadro.setImageResource(imagen);


    }

}
