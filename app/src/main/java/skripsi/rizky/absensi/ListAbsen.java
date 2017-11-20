package skripsi.rizky.absensi;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;

import skripsi.rizky.absensi.absen.DownloaderAbsen;
import skripsi.rizky.absensi.matakuliah.Downloader;
import skripsi.rizky.absensi.network.config.Config;

/**
 * Created by ASUS on 19/08/2017.
 */

public class ListAbsen extends AppCompatActivity {

    final static String API_MATAKULIAH = "http://192.168.43.212/absensi/MataKuliah/mataKuliah.php";

    //    final static String API_MATAKULIAH = "http://aksesblk-samarinda.com/absensi/MataKuliah/mataKuliah.php";
//    static String urlAddress = "http://192.168.43.212/absensi/MataKuliah/listAbsen.php?nama_dosen=";
    public String nama_dosen, pertemuan;
    public String urlAddress, DATA_URL_ABSEN, DATA_URL_SAKIT, DATA_URL_IZIN;
    private EditText editTextNamaDosen;
    private Spinner spnPilihMatakuliah;
    private TextView textJumlahAbsen, textJumlahSakit, textJumlahIzin;


//    static String urlAddress = "http://aksesblk-samarinda.com/absensi/MataKuliah/listAbsen.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_absen_siswa);

        textJumlahAbsen = (TextView) findViewById(R.id.textJumlahAbsen);
        textJumlahSakit = (TextView) findViewById(R.id.textJumlahSakit);
        textJumlahIzin = (TextView) findViewById(R.id.textJumlahIzin);

        editTextNamaDosen = (EditText) findViewById(R.id.editTextNamaDosen);
        spnPilihMatakuliah = (Spinner) findViewById(R.id.spnPilihMatakuliah);
        Button btnLiatAbsen = (Button) findViewById(R.id.btnLiatAbsenSiswa);


        final RecyclerView recyclerViewAbsen = (RecyclerView) findViewById(R.id.recyclerViewAbsen);
        recyclerViewAbsen.setLayoutManager(new LinearLayoutManager(this));

        final SwipeRefreshLayout swipeRefreshLayoutAbsen = (SwipeRefreshLayout) findViewById(R.id.swipeLayoutAbsen);


//        new DownloaderAbsen(ListAbsen.this, urlAddress, recyclerViewAbsen, swipeRefreshLayoutAbsen).execute();

        btnLiatAbsen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getData();
                getDataSakit();
                getDataIzin();
//                nama_dosen = editTextNamaDosen.getText().toString();

                pertemuan = editTextNamaDosen.getText().toString();

                nama_dosen = spnPilihMatakuliah.getSelectedItem().toString();


//                if (TextUtils.isEmpty(nama_dosen)) {
//                    editTextNamaDosen.setError("Field tidak boleh kosong");
//                    return;
//                }


                try {
                    urlAddress = "http://192.168.43.212/absensi/MataKuliah/listAbsen.php?kode_matkul=" + URLEncoder.encode(nama_dosen, "UTF-8") + "&pertemuan=" + URLEncoder.encode(pertemuan, "UTF-8");
//                    urlAddress = "http://aksesblk-samarinda.com/absensi/MataKuliah/listAbsen.php?kode_matkul=" +URLEncoder.encode(nama_dosen, "UTF-8") + "&pertemuan=" + URLEncoder.encode(pertemuan, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


//                urlAddress = urlAddress.replaceAll(urlAddress,"%20");
//                URL sourceURl = new URL(urlAddress);

                new DownloaderAbsen(ListAbsen.this, urlAddress, recyclerViewAbsen, swipeRefreshLayoutAbsen).execute();


//                editTextNamaDosen.setText("");


            }
        });

        swipeRefreshLayoutAbsen.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                try {
                    urlAddress = "http://192.168.43.212/absensi/MataKuliah/listAbsen.php?kode_matkul=" + URLEncoder.encode(nama_dosen, "UTF-8") + "&pertemuan=" + URLEncoder.encode(pertemuan, "UTF-8");
//                    urlAddress = "http://aksesblk-samarinda.com/absensi/MataKuliah/listAbsen.php?kode_matkul=" +URLEncoder.encode(nama_dosen, "UTF-8") + "&pertemuan=" + URLEncoder.encode(pertemuan, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                new DownloaderAbsen(ListAbsen.this, urlAddress, recyclerViewAbsen, swipeRefreshLayoutAbsen).execute();
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        new Downloader(ListAbsen.this, API_MATAKULIAH, spnPilihMatakuliah).execute();
//        getData();
//        getDataSakit();
    }

    private void getData() {

        pertemuan = editTextNamaDosen.getText().toString();

        nama_dosen = spnPilihMatakuliah.getSelectedItem().toString();

        try {
            DATA_URL_ABSEN = "http://192.168.43.212/absensi/MataKuliah/jumlahAbsen.php?kode_matkul=" + URLEncoder.encode(nama_dosen, "UTF-8") + "&pertemuan=" + URLEncoder.encode(pertemuan, "UTF-8");
//            DATA_URL_ABSEN = "http://aksesblk-samarinda.com/absensi/MataKuliah/jumlahAbsen.php?kode_matkul=" +URLEncoder.encode(nama_dosen, "UTF-8") + "&pertemuan=" + URLEncoder.encode(pertemuan, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        String url = DATA_URL_ABSEN;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                showJSON(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ListAbsen.this, "Error Data Tidak Tampil", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response) {

        String jumlah = "";
        try {

            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);
            JSONObject jumlahAbsen = result.getJSONObject(0);
            jumlah = jumlahAbsen.getString(Config.KEY_NAME);

        } catch (JSONException ex) {

            ex.printStackTrace();
        }

        textJumlahAbsen.setText("Jumlah yang telah absen: " + jumlah + " /40");

    }

    private void getDataSakit() {

        pertemuan = editTextNamaDosen.getText().toString();

        nama_dosen = spnPilihMatakuliah.getSelectedItem().toString();

        try {
            DATA_URL_SAKIT = "http://192.168.43.212/absensi/MataKuliah/jumlahSakit.php?kode_matkul=" + URLEncoder.encode(nama_dosen, "UTF-8") + "&pertemuan=" + URLEncoder.encode(pertemuan, "UTF-8");
//            DATA_URL_SAKIT = "http://aksesblk-samarinda.com/absensi/MataKuliah/jumlahIzin.php?kode_matkul=" +URLEncoder.encode(nama_dosen, "UTF-8") + "&pertemuan=" + URLEncoder.encode(pertemuan, "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        String url = DATA_URL_SAKIT;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                showJSONSakit(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ListAbsen.this, "Error Data Tidak Tampil", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSONSakit(String response) {

        String sakit = "";
        try {

            JSONObject jsonObject = new JSONObject(response);
            JSONArray hasil = jsonObject.getJSONArray(Config.JSON_ARRAY_SAKIT);
            JSONObject jumlahSakit = hasil.getJSONObject(0);
            sakit = jumlahSakit.getString(Config.KEY_NAME_SAKIT);

        } catch (JSONException ex) {

            ex.printStackTrace();
        }

        textJumlahSakit.setText("Jumlah yang Sakit : " + sakit + " Orang");

    }

    private void getDataIzin() {

        pertemuan = editTextNamaDosen.getText().toString();
        nama_dosen = spnPilihMatakuliah.getSelectedItem().toString();

        try {

            DATA_URL_IZIN = "http://192.168.43.212/absensi/MataKuliah/jumlahIzin.php?kode_matkul=" + URLEncoder.encode(nama_dosen, "UTF-8") + "&pertemuan=" + URLEncoder.encode(pertemuan, "UTF-8");

        } catch (UnsupportedEncodingException ex) {

            ex.printStackTrace();
        }

        String url = DATA_URL_IZIN;
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                showJSONIzin(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(ListAbsen.this, "Error Data Tidak Tampil", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSONIzin(String response) {

        String izin = "";

        try {

            JSONObject jsonObject = new JSONObject(response);
            JSONArray hasil = jsonObject.getJSONArray(Config.JSON_ARRAY_IZIN);
            JSONObject jumlahIzin = hasil.getJSONObject(0);
            izin = jumlahIzin.getString(Config.KEY_NAME_IZIN);

        } catch (JSONException ex) {

            ex.printStackTrace();
        }

        textJumlahIzin.setText("Jumlah yang Izin : " + izin + " Orang");
    }


}
