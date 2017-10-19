package skripsi.rizky.absensi;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import skripsi.rizky.absensi.verifikasi.DownloaderVerifikasi;

public class ListVerfikasiAbsen extends AppCompatActivity {

    static String urlAddress = "http://192.168.43.212/absensi/MataKuliah/viewAbsenMahasiswa.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_verfikasi_absen);

        final RecyclerView recyclerViewVerifikasi = (RecyclerView) findViewById(R.id.recyclerViewVerifikasi);
        recyclerViewVerifikasi.setLayoutManager(new LinearLayoutManager(this));
        final SwipeRefreshLayout swipeRefreshLayoutVerifikasi = (SwipeRefreshLayout) findViewById(R.id.swipeLayoutVerifikasi);

        new DownloaderVerifikasi(ListVerfikasiAbsen.this, urlAddress, recyclerViewVerifikasi, swipeRefreshLayoutVerifikasi).execute();
        swipeRefreshLayoutVerifikasi.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new DownloaderVerifikasi(ListVerfikasiAbsen.this, urlAddress, recyclerViewVerifikasi, swipeRefreshLayoutVerifikasi).execute();
            }
        });
    }
}

