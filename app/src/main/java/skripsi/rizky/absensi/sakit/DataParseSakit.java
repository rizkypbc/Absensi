package skripsi.rizky.absensi.sakit;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import skripsi.rizky.absensi.adapter.ListSakitAdapter;
import skripsi.rizky.absensi.model.SakitData;

/**
 * Created by ASUS on 16/10/2017.
 */

public class DataParseSakit extends AsyncTask<Void, Void, Boolean> {

    Context context;
    String jsonData;
    RecyclerView rv;
    SwipeRefreshLayout swipeRefreshLayout;

    ArrayList<SakitData> sakitDatas = new ArrayList<>();

    public DataParseSakit(Context context, String jsonData, RecyclerView rv, SwipeRefreshLayout swipeRefreshLayout) {
        this.context = context;
        this.jsonData = jsonData;
        this.rv = rv;
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        return this.parseData();
    }

    @Override
    protected void onPostExecute(Boolean isParsed) {
        super.onPostExecute(isParsed);

        swipeRefreshLayout.setRefreshing(false);

        if (isParsed) {

            rv.setAdapter(new ListSakitAdapter(context, sakitDatas));
        } else {

            Toast.makeText(context, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean parseData() {

        try {

            JSONArray jsonArray = new JSONArray(jsonData);
            JSONObject jsonObject;

            sakitDatas.clear();
            SakitData sakitDataa;

            for (int i = 0; i < jsonArray.length(); i++) {

                jsonObject = jsonArray.getJSONObject(i);

                String id = jsonObject.getString("id_sesi");
                String materi = jsonObject.getString("materi");
                String dosen = jsonObject.getString("nama_dosen");
                String pertemuan = jsonObject.getString("pertemuan");
                String ruang = jsonObject.getString("ruang");
                String tanggal = jsonObject.getString("tanggal");
                String waktu = jsonObject.getString("waktu");
                String keterangan = jsonObject.getString("keterangan");
                String matkul = jsonObject.getString("kode_matkul");

                sakitDataa = new SakitData();

                sakitDataa.setId_sesi(id);
                sakitDataa.setMateri(materi);
                sakitDataa.setNama_dosen(dosen);
                sakitDataa.setPertemuan(pertemuan);
                sakitDataa.setRuang(ruang);
                sakitDataa.setTanggal(tanggal);
                sakitDataa.setWaktu(waktu);
                sakitDataa.setKeterangan(keterangan);
                sakitDataa.setKode_matkul(matkul);

                sakitDatas.add(sakitDataa);
            }

            return true;
        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return false;
    }
}
