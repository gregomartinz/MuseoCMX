package cmx.acuntia.es.museocmx;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MapActivity extends AppCompatActivity {

    ImageView img;

    static JSONObject jObj = null;
    static JSONObject positionObj = null;
    static Bitmap bitmap;
    static String dirMap;
    String imgMap = "";
    Double imgx = 0.0;
    Double imgy = 0.0;
    Double x = 0.0;
    Double y = 0.0;
    private Matrix matrix = new Matrix();
    private float scale = 0.4f;
    private ScaleGestureDetector SGD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("Mapa del museo");
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

        img = (ImageView) findViewById(R.id.imageView);
        SGD = new ScaleGestureDetector(this,new ScaleListener());

        try {
            jObj  = new JSONObject(getIntent().getStringExtra("Json"));
            downloadMap();
            getZone();
            drawPoint(bitmap);
        } catch (JSONException | InterruptedException | ExecutionException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_refresh:
                try {
                    descarga();
                    getZone();
                    drawPoint(bitmap);
                } catch (JSONException | InterruptedException | ExecutionException | IOException e) {
                    e.printStackTrace();
                }
                return true;
            case R.id.action_settings:
                //metodoSettings()
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void descarga() throws IOException, JSONException, InterruptedException, ExecutionException {

        DownloadTask d = new DownloadTask();
        d.execute();
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

    private void getJSONData() throws JSONException {
        positionObj = jObj.getJSONObject("mapCoordinate");
        JSONObject aux = jObj.getJSONObject("mapInfo");
        JSONObject aux2 = aux.getJSONObject("image");
        imgMap = aux2.getString("imageName");
    }

    private void downloadMap() throws IOException, JSONException, ExecutionException, InterruptedException {

        getJSONData();
        dirMap = "http://" + MainActivity.URLSERVER + "/api/config/v1/maps/imagesource/" + imgMap;
//        ImageTask task = new ImageTask();
//        task.execute(dirMap);
        bitmap = new ImageTask().execute().get();
        imgx = (double) bitmap.getWidth();
        imgy = (double) bitmap.getHeight();
    }

    private void getMapSize() throws InterruptedException {
        imgx = (double) bitmap.getWidth();
        imgy = (double) bitmap.getHeight();
    }

    public void drawPoint(Bitmap b){
        Paint currentPaint;
        currentPaint = new Paint();
        currentPaint.setDither(true);
        currentPaint.setColor(0xFF0000FF);  // alpha.r.g.b
        currentPaint.setStyle(Paint.Style.STROKE);
        currentPaint.setStrokeJoin(Paint.Join.ROUND);
        currentPaint.setStrokeCap(Paint.Cap.ROUND);
        currentPaint.setStrokeWidth(20);

        Bitmap tempBitmap = Bitmap.createBitmap(b.getWidth(), b.getHeight(), Bitmap.Config.RGB_565);
        Canvas tempCanvas = new Canvas(tempBitmap);
        tempCanvas.drawBitmap(bitmap, 0, 0, null);

        tempCanvas.drawCircle(Float.valueOf(String.valueOf(x)),Float.valueOf(String.valueOf(y)),10,currentPaint);
        img.setImageDrawable(new BitmapDrawable(getResources(), tempBitmap));
    }

    public void getZone() throws JSONException, InterruptedException, IOException, ExecutionException {

        getJSONData();
        JSONObject mapInfo = jObj.getJSONObject("mapInfo");
        JSONObject floorDimension = mapInfo.getJSONObject("floorDimension");
        Integer mapx = floorDimension.getInt("width");
        Integer mapy = floorDimension.getInt("length");
        getMapSize();
        Double posx = positionObj.getDouble("x");
        Double posy = positionObj.getDouble("y");

        if(imgx == 0 && imgy == 0){
            imgx = 1.0;
            imgy = 1.0;
        }

        Double propx = imgx / mapx;
        Double propy = imgy / mapy;

        x =  posx*propx;
        y =  posy*propy;

    }

    public boolean onTouchEvent(MotionEvent ev) {
        SGD.onTouchEvent(ev);
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scale *= detector.getScaleFactor();
            scale = Math.max(0.1f, Math.min(scale, 5.0f));

            matrix.setScale(scale, scale);
            img.setImageMatrix(matrix);
            return true;
        }
    }

}
