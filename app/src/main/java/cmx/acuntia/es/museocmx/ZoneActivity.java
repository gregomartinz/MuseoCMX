package cmx.acuntia.es.museocmx;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ZoneActivity extends AppCompatActivity{

    TextView textUbic;
    ListView lista;
    String ubicacion;
    ArrayAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("Que ver por aquí");
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

        lista = (ListView)findViewById(R.id.listView);
        adaptador = new CuadroArrayAdapter(this,R.layout.list_item,Datasource.GOYA);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intento = new Intent(ZoneActivity.this, DetalleCuadroActivity.class);
                Cuadro c = (Cuadro) lista.getItemAtPosition(position);
                intento.putExtra("nombreCuadro",c.getNombre());
                intento.putExtra("cuadroDetail", c.getDescripcion());
                intento.putExtra("imagen", c.getImagen());
                startActivity(intento);
            }
        });


        textUbic = (TextView) findViewById(R.id.textView);
        ubicacion = getIntent().getStringExtra("ubicacion");
        textUbic.setText(ubicacion);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
