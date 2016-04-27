package cmx.acuntia.es.museocmx;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    ImageButton botonMap;
    ImageButton botonZona;
    static JSONObject jObj = null;
    static JSONObject positionObj = null;
    String ubicacion = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Aquí se edita el toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (toolbar != null) {
            toolbar.setTitle("Bienvenido al museo");
            toolbar.setLogo(R.mipmap.ic_launcher);
        }

        botonMap = (ImageButton) findViewById(R.id.imageButton);
        botonZona = (ImageButton) findViewById(R.id.imageButton2);
        if (botonMap != null && botonZona != null) {
            botonMap.setImageResource(R.drawable.mapsicon);
            botonZona.setImageResource(R.drawable.painting2);
        }

        botonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    descarga();
                } catch (IOException | JSONException | ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intento = new Intent(MainActivity.this, MapActivity.class);
                intento.putExtra("Json",jObj.toString());
                startActivity(intento);
            }
        });
        botonZona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    getZone();
                } catch (JSONException | InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intento = new Intent(MainActivity.this, ZoneActivity.class);
                intento.putExtra("ubicacion",ubicacion);
                startActivity(intento);
            }
        });
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

    private void descarga() throws IOException, JSONException, InterruptedException, ExecutionException {

        String mac = getWifiMacAddress();
        jObj = new DownloadTask().execute(mac).get();

    }

    public static String getWifiMacAddress() {
        try {
            String interfaceName = "wlan0";
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                if (!intf.getName().equalsIgnoreCase(interfaceName)){
                    continue;
                }

                byte[] mac = intf.getHardwareAddress();
                if (mac==null){
                    return "";
                }

                StringBuilder buf = new StringBuilder();
                for (byte aMac : mac) {
                    buf.append(String.format("%02X:", aMac));
                }
                if (buf.length()>0) {
                    buf.deleteCharAt(buf.length() - 1);
                }
                return buf.toString();
            }
        } catch (Exception ignored) { } // for now eat exceptions
        return "";
    }


    public void getZone() throws JSONException, InterruptedException {
        positionObj = jObj.getJSONObject("mapCoordinate");

        Double posx = positionObj.getDouble("x");
        Double posy = positionObj.getDouble("y");


        if(posy<60 && posx<105 || 45<posy && posy<55 && posx<140){
            ubicacion = "FORMACION";
        }if (posy>60 && posx<135){
            ubicacion = "PYCLUCAS";
        }if (posy<45 && posx>105 && posx<140 || posx>139 && posx<172 && posy<55 || posx>172 && posx<200 && posy<61 || posx>200 && posx<240 && posy<75){
            ubicacion = "SOPORTE";
        }if (posx>139 && posx<172 && posy>84 || posx>172 && posx<200 && posy>79 || posx>200 && posx<240 && posy>75){
            ubicacion = "PYCALBERTO";
        }if (posy>55 && posy<85 && posx>113 && posx<160){
            ubicacion = "HALL";
        }
    }
}
