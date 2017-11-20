package skripsi.rizky.absensi.absen;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import skripsi.rizky.absensi.adapter.ListAbsenAdapter;
import skripsi.rizky.absensi.model.AbsenData;

/**
 * Created by ASUS on 19/08/2017.
 */

public class DataParserAbsen extends AsyncTask<Void, Void, Boolean> {

    ArrayList<AbsenData> absenDatas = new ArrayList<>();

    Context context;
    String jsonData;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;

    public DataParserAbsen(Context context, String jsonData, RecyclerView recyclerView, SwipeRefreshLayout swipeRefreshLayout) {


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

            recyclerView.setAdapter(new ListAbsenAdapter(context, absenDatas));
        } else {

            Toast.makeText(context, "Unable to Parse", Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean parseData() {

        try {

            JSONArray jsonArray = new JSONArray(jsonData);
            JSONObject jsonObject;

            absenDatas.clear();
            AbsenData absen;

            for (int i = 0; i < jsonArray.length(); i++) {

                jsonObject = jsonArray.getJSONObject(i);

                String nim = jsonObject.getString("nim");
                String nama = jsonObject.getString("nama");
                String jurusan = jsonObject.getString("jurusan");
                String tanggal = jsonObject.getString("tanggal_absen");
                String waktu = jsonObject.getString("waktu_absen");
//                String idSesi = jsonObject.getString("id_sesi");
                String mataKuliah = jsonObject.getString("kode_matkul");
                String keterangan_absen = jsonObject.getString("keterangan_absen");

                absen = new AbsenData();

//                absen.setId_sesi(idSesi);
                absen.setNim(nim);
                absen.setNama(nama);
                absen.setJurusan(jurusan);
                absen.setKode_matkul(mataKuliah);
                absen.setTanggal_absen(tanggal);
                absen.setWaktu_absen(waktu);
                absen.setKeterangan_absen(keterangan_absen);

                absenDatas.add(absen);
            }

            return true;
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return false;
    }
}
