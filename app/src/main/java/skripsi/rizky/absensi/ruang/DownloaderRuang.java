package skripsi.rizky.absensi.ruang;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import skripsi.rizky.absensi.matakuliah.Connector;

/**
 * Created by ASUS on 25/08/2017.
 */

public class DownloaderRuang extends AsyncTask<Void, Void, String> {

    Context context;
    String urlAddress;
    Spinner spinner;
    ProgressDialog progressDialog;

    public DownloaderRuang(Context context, String urlAddress, Spinner spinner) {
        this.context = context;
        this.urlAddress = urlAddress;
        this.spinner = spinner;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Fetch");
        progressDialog.setMessage("Fetching...Please wait");
        progressDialog.show();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return this.downloadData();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        progressDialog.dismiss();

        if (s == null) {

            Toast.makeText(context, "Gagal Mengambil Data Ruang", Toast.LENGTH_SHORT).show();
        } else {

            Toast.makeText(context, "Berhasil Mengambil Data Ruang", Toast.LENGTH_SHORT).show();

            DataParserRuang parse = new DataParserRuang(context, spinner, s);
            parse.execute();
        }
    }

    private String downloadData() {

        HttpURLConnection con = Connector.connect(urlAddress);
        if (con == null) {
            return null;
        }

        InputStream is = null;
        try {

            is = new BufferedInputStream(con.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line = null;
            StringBuffer response = new StringBuffer();

            if (br != null) {
                while ((line = br.readLine()) != null) {
                    response.append(line + "\n");
                }

                br.close();

            } else {
                return null;
            }


            return response.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return null;
    }
}

