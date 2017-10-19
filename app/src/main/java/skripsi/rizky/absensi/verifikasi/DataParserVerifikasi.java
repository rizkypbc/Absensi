package skripsi.rizky.absensi.verifikasi;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import skripsi.rizky.absensi.adapter.ListVerifikasiAdapter;
import skripsi.rizky.absensi.model.VerifikasiData;

/**
 * Created by ASUS on 20/10/2017.
 */

public class DataParserVerifikasi extends AsyncTask<Void, Void, Boolean> {

    ArrayList<VerifikasiData> verifikasiDatas = new ArrayList<>();

    Context context;
    String jsonData;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;

    public DataParserVerifikasi(Context context, String jsonData, RecyclerView recyclerView, SwipeRefreshLayout swipeRefreshLayout) {
        this.context = context;
        this.jsonData = jsonData;
        this.recyclerView = recyclerView;
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        return this.parseData();
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);

        swipeRefreshLayout.setRefreshing(false);
        if (aBoolean) {

            recyclerView.setAdapter(new ListVerifikasiAdapter(context, verifikasiDatas));
        } else {

            Toast.makeText(context, "Unable to Parses", Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean parseData() {

        try {

            JSONArray jsonArray = new JSONArray(jsonData);
            JSONObject jsonObject;

            verifikasiDatas.clear();
            VerifikasiData verifikasi;

            for (int i = 0; i < jsonArray.length(); i++) {

                jsonObject = jsonArray.getJSONObject(i);

                String id = jsonObject.getString("id");
                String nim = jsonObject.getString("nim");
                String nama = jsonObject.getString("nama");
                String jurusan = jsonObject.getString("jurusan");
                String tanggal = jsonObject.getString("tanggal_absen");
                String waktu = jsonObject.getString("waktu_absen");
                String mataKuliah = jsonObject.getString("kode_matkul");
                String keterangan_absen = jsonObject.getString("keterangan_absen");
                String token = jsonObject.getString("token_absen");

                verifikasi = new VerifikasiData();

                verifikasi.setId(id);
                verifikasi.setNim(nim);
                verifikasi.setNama(nama);
                verifikasi.setJurusan(jurusan);
                verifikasi.setKode_matkul(mataKuliah);
                verifikasi.setTanggal_absen(tanggal);
                verifikasi.setWaktu_absen(waktu);
                verifikasi.setKeterangan_absen(keterangan_absen);
                verifikasi.setToken_absen(token);

                verifikasiDatas.add(verifikasi);
            }

            return true;
        } catch (JSONException ex) {

            ex.printStackTrace();
        }

        return false;
    }
}


