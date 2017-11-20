package skripsi.rizky.absensi.ruang;

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
 * Created by ASUS on 25/08/2017.
 */

public class DataParserRuang extends AsyncTask<Void, Void, Integer> {

    Context context;
    Spinner spinner;
    String jsonData;
    ProgressDialog progressDialog;
    ArrayList<String> ruang = new ArrayList<>();


    public DataParserRuang(Context context, Spinner spinner, String jsonData) {
        this.context = context;
        this.spinner = spinner;
        this.jsonData = jsonData;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Parse");
        progressDialog.setMessage("Parsing... Mohon tunggu sebentar");
        progressDialog.show();
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        return this.parseData();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);

        progressDialog.dismiss();

        if (integer == 0) {

            Toast.makeText(context, "Unable to Parse", Toast.LENGTH_SHORT).show();
        } else {

            ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, ruang);
            spinner.setAdapter(adapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
    }

    private int parseData() {

        try {

            JSONArray jsonArray = new JSONArray(jsonData);
            JSONObject jsonObject = null;

            ruang.clear();
            RuangData ruangs = null;

            for (int i = 0; i < jsonArray.length(); i++) {

                jsonObject = jsonArray.getJSONObject(i);

                final String kode_ruang = jsonObject.getString("kode_ruang");

                ruangs = new RuangData();
                ruangs.setKode_ruang(kode_ruang);

                ruang.add(kode_ruang);
            }

            return 1;
        } catch (JSONException ex) {

            ex.printStackTrace();
        }

        return 0;
    }
}
