package skripsi.rizky.absensi.kelas;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import skripsi.rizky.absensi.matakuliah.Connector;
import skripsi.rizky.absensi.sesi.DataParserSesi;

/**
 * Created by ASUS on 15/08/2017.
 */

public class DownloaderKelas extends AsyncTask<Void, Void, String> {

    Context context;
    String urlAddress;
    RecyclerView rv;
    SwipeRefreshLayout swipeRefreshLayout;

    public DownloaderKelas(Context context, String urlAddress, RecyclerView rv, SwipeRefreshLayout swipeRefreshLayout) {

        this.context = context;
        this.urlAddress = urlAddress;
        this.rv = rv;
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if (!swipeRefreshLayout.isRefreshing()) {

            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    protected String doInBackground(Void... params) {
        return this.downloadData();
    }

    @Override
    protected void onPostExecute(String jsonData) {
        super.onPostExecute(jsonData);

        if (jsonData == null) {

            swipeRefreshLayout.setRefreshing(false);
            Toast.makeText(context, "Unsuccessful, Unable to Retrieve", Toast.LENGTH_SHORT).show();
        } else {

            new DataParserKelas(context, jsonData, rv, swipeRefreshLayout).execute();
        }
    }

    private String downloadData() {

        HttpURLConnection con = Connector.connect(urlAddress);
        if (con == null) {

            return null;
        }
        try {

            InputStream is = new BufferedInputStream(con.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;
            StringBuffer jsonData = new StringBuffer();

            while ((line = br.readLine()) != null) {

                jsonData.append(line + "\n");
            }

            br.close();
            is.close();

            return jsonData.toString();


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
