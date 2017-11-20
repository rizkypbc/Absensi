package skripsi.rizky.absensi.matakuliah;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ASUS on 08/08/2017.
 */

public class DataParser extends AsyncTask<Void, Void, Integer> {

    Context c;
    Spinner sp;
    String jsonData;

    ProgressDialog pd;
    ArrayList<String> matakuliah = new ArrayList<>();

    public DataParser(Context c, Spinner sp, String jsonData) {
        this.c = c;
        this.sp = sp;
        this.jsonData = jsonData;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();


        pd = new ProgressDialog(c);
        pd.setTitle("Parse");
        pd.setMessage("Parsing...Please wait");
        pd.show();
    }

    @Override
    protected Integer doInBackground(Void... params) {
        return this.parseData();
    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);

        pd.dismiss();

        if (result == 0) {
            Toast.makeText(c, "Unable To Parse", Toast.LENGTH_SHORT).show();
        } else {

            //BIND
            ArrayAdapter adapter = new ArrayAdapter(c, android.R.layout.simple_list_item_1, matakuliah);
            sp.setAdapter(adapter);

            sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }
    }

    private int parseData() {
        try {
            JSONArray ja = new JSONArray(jsonData);
            JSONObject jo = null;

            matakuliah.clear();
            MataKuliahData s = null;

            for (int i = 0; i < ja.length(); i++) {
                jo = ja.getJSONObject(i);

                final String kode_matkul = jo.getString("kode_matkul");
                final String nama_matkul = jo.getString("nama_matkul");

                s = new MataKuliahData();
                s.setKode_matkul(kode_matkul);
                s.setNama_matkul(nama_matkul);

                matakuliah.add(nama_matkul);
            }

            return 1;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;

    }
}
