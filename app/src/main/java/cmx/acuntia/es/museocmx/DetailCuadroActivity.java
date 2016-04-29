package cmx.acuntia.es.museocmx;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class DetailCuadroActivity extends AppCompatActivity {

    TextView detalle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_cuadro);
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

        detalle = (TextView)findViewById(R.id.textDetail);

        String cuadro = getIntent().getStringExtra("cuadroDetail");

        detalle.setText(cuadro);


    }

}
