package cmx.acuntia.es.museocmx;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class MapActivity extends AppCompatActivity {

    ImageView img;

    static JSONObject jObj = null;
    static JSONObject positionObj = null;
    Bitmap bitmap;
    String imgMap = "";
    Double imgx = 0.0;
    Double imgy = 0.0;
    Double x = 0.0;
    Double y = 0.0;

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

    private void getJSONData() throws JSONException {
        positionObj = jObj.getJSONObject("mapCoordinate");
        JSONObject aux = jObj.getJSONObject("mapInfo");
        JSONObject aux2 = aux.getJSONObject("image");
        imgMap = aux2.getString("imageName");
    }

    private void downloadMap() throws IOException, JSONException, ExecutionException, InterruptedException {

        getJSONData();
        String dir = "http://192.168.104.24/api/config/v1/maps/imagesource/" + imgMap;
        bitmap = new ImageTask().execute(dir).get();
        imgx = (double) bitmap.getWidth();
        imgy = (double) bitmap.getHeight();
    }

    private void getMapSize(){
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

}
