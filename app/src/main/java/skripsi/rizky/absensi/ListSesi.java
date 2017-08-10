package skripsi.rizky.absensi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kosalgeek.android.json.JsonConverter;

import java.util.ArrayList;

import skripsi.rizky.absensi.adapter.ListSesiAdapter;
import skripsi.rizky.absensi.model.SesiData;

public class ListSesi extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sesi);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        String url = "http://10.223.215.101/absensi/MataKuliah/viewMataKuliah.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                ArrayList<SesiData> sesiDatas = new JsonConverter<SesiData>()
                        .toArrayList(response, SesiData.class);

                ListSesiAdapter adapter = new ListSesiAdapter(getApplicationContext(), sesiDatas);

                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                if (error != null) {

                    Toast.makeText(getApplicationContext(), "Periksa Koneksi Internet Anda", Toast.LENGTH_SHORT).show();
                }
            }
        });

        MySingelton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
        return;
    }
}
