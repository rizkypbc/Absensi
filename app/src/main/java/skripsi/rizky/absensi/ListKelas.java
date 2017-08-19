package skripsi.rizky.absensi;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import skripsi.rizky.absensi.kelas.DownloaderKelas;
import skripsi.rizky.absensi.matakuliah.Downloader;
import skripsi.rizky.absensi.sesi.DownloaderSesi;

public class ListKelas extends AppCompatActivity {

    static String urlAddress = "http://192.168.43.212/absensi/MataKuliah/viewSesi.php";

//    static String urlAddress = "http://10.223.217.154/absensi/MataKuliah/viewSesi.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_kelas);

        final RecyclerView recyclerViewKelas = (RecyclerView) findViewById(R.id.recyclerViewKelas);
        recyclerViewKelas.setLayoutManager(new LinearLayoutManager(this));
        final SwipeRefreshLayout swipeRefreshLayoutKelas = (SwipeRefreshLayout) findViewById(R.id.swipeLayoutKelas);

        new DownloaderKelas(ListKelas.this, urlAddress, recyclerViewKelas, swipeRefreshLayoutKelas).execute();

        swipeRefreshLayoutKelas.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new DownloaderKelas(ListKelas.this, urlAddress, recyclerViewKelas, swipeRefreshLayoutKelas).execute();
            }
        });
    }
}
