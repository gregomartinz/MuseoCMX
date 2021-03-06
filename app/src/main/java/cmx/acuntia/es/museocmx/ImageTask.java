package cmx.acuntia.es.museocmx;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ImageTask extends AsyncTask<String, Integer, Bitmap> {

    private URL url;

    @Override
    protected void onPreExecute() {
        try {
            url = new URL(MapActivity.dirMap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Bitmap doInBackground(String... params) {

        InputStream is = null;
        try {
            URLConnection urlConnection = url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setRequestProperty("authorization", "Basic YWRtaW46QWN1bnQxYQ==");
            urlConnection.setRequestProperty("cache-control", "no-cache");
            urlConnection.setConnectTimeout(1000);
            is = urlConnection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return BitmapFactory.decodeStream(is);
    }

    @Override
    protected void onPostExecute(Bitmap b) {
        MapActivity.bitmap = b;
    }
}
