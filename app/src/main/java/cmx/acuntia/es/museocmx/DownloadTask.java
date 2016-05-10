package cmx.acuntia.es.museocmx;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class DownloadTask extends AsyncTask<String, Integer, JSONObject> {


    @Override
    protected JSONObject doInBackground(String... params) {

        InputStream is = null;
        String json = "";
        JSONArray jarray;
        JSONObject jObj = null;
        String URLServer = MainActivity.URLSERVER;

        try {
            String mac = params[0];
            mac = mac.toLowerCase();
            mac = URLEncoder.encode(mac, "UTF-8");
            if (URLServer == null){
                URLServer = "192.168.104.24";
            }
            MainActivity.URLSERVER = URLServer;
            String dir = "http://" + URLServer + "/api/location/v2/clients?macAddress=" + mac;
            URL url = new URL(dir);
//            Log.d("la url es", url.toString());
            URLConnection urlConnection = url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setRequestProperty("authorization", "Basic YWRtaW46QWN1bnQxYQ==");
            urlConnection.setRequestProperty("cache-control", "no-cache");
            urlConnection.setConnectTimeout(1000);
            is = urlConnection.getInputStream();

        } catch (IOException e) {
            e.printStackTrace();
        }
        //Read the content of the GET method
        try {
            assert is != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            is.close();
            json = sb.toString();
//            Log.d("Lo que se baja", json);
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }
        //Try parse the string to a JSON object

        try {
            jarray = new JSONArray(json);
            jObj = jarray.getJSONObject(0);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
        return jObj;
    }

    @Override
    protected void onPostExecute(JSONObject object) {
        MapActivity.jObj = object;
    }
}
