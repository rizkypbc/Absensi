package skripsi.rizky.absensi.sesi;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import skripsi.rizky.absensi.adapter.CustomAdapter;
import skripsi.rizky.absensi.model.SesiData;

/**
 * Created by ASUS on 14/08/2017.
 */

public class DataParserSesi extends AsyncTask<Void, Void, Boolean> {

    Context context;
    String jsonData;
    RecyclerView rv;
    SwipeRefreshLayout swipeRefreshLayout;

    ArrayList<SesiData> sesiDatas = new ArrayList<>();

    public DataParserSesi(Context context, String jsonData, RecyclerView rv, SwipeRefreshLayout swipeRefreshLayout) {

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

            rv.setAdapter(new CustomAdapter(context, sesiDatas));
        } else {

            Toast.makeText(context, "Unable to parse", Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean parseData() {

        try {

            JSONArray jsonArray = new JSONArray(jsonData);
            JSONObject jsonObject;

            sesiDatas.clear();
            SesiData sesiDataa;

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

                sesiDataa = new SesiData();

                sesiDataa.setId_sesi(id);
                sesiDataa.setMateri(materi);
                sesiDataa.setNama_dosen(dosen);
                sesiDataa.setPertemuan(pertemuan);
                sesiDataa.setRuang(ruang);
                sesiDataa.setTanggal(tanggal);
                sesiDataa.setWaktu(waktu);
                sesiDataa.setKeterangan(keterangan);
                sesiDataa.setKode_matkul(matkul);

                sesiDatas.add(sesiDataa);
            }

            return true;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return false;
    }
}
