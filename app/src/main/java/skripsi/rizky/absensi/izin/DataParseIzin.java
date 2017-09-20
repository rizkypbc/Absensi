package skripsi.rizky.absensi.izin;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import skripsi.rizky.absensi.adapter.ListIzinAdapter;
import skripsi.rizky.absensi.model.IzinData;

/**
 * Created by ASUS on 19/09/2017.
 */

public class DataParseIzin extends AsyncTask<Void, Void, Boolean> {

    Context context;
    String jsonData;
    RecyclerView rv;
    SwipeRefreshLayout swipeRefreshLayout;

    ArrayList<IzinData> izinDatas = new ArrayList<>();

    public DataParseIzin(Context context, String jsonData, RecyclerView rv, SwipeRefreshLayout swipeRefreshLayout) {
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

            rv.setAdapter(new ListIzinAdapter(context, izinDatas));
        } else {

            Toast.makeText(context, "Gagal Mengambil Data", Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean parseData() {

        try {

            JSONArray jsonArray = new JSONArray(jsonData);
            JSONObject jsonObject;

            izinDatas.clear();
            IzinData izinDataa;

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

                izinDataa = new IzinData();

                izinDataa.setId_sesi(id);
                izinDataa.setMateri(materi);
                izinDataa.setNama_dosen(dosen);
                izinDataa.setPertemuan(pertemuan);
                izinDataa.setRuang(ruang);
                izinDataa.setTanggal(tanggal);
                izinDataa.setWaktu(waktu);
                izinDataa.setKeterangan(keterangan);
                izinDataa.setKode_matkul(matkul);

                izinDatas.add(izinDataa);
            }

            return true;
        } catch (JSONException e) {

            e.printStackTrace();
        }

        return false;
    }
}
