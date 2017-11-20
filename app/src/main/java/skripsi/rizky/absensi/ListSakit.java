package skripsi.rizky.absensi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import skripsi.rizky.absensi.izin.DownloaderIzin;
import skripsi.rizky.absensi.sakit.DownloaderSakit;

/**
 * Created by ASUS on 16/10/2017.
 */

public class ListSakit extends AppCompatActivity {

    static String urlAddress = "http://192.168.43.212/absensi/MataKuliah/viewSesiMahasiswa.php";
//    static String urlAddress = "http://aksesblk-samarinda.com/absensi/MataKuliah/viewSesiMahasiswa.php";

    public static void start(Context context) {

        Intent intent = new Intent(context, ListSakit.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_sakit);

        final RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerViewSakit);
        rv.setLayoutManager(new LinearLayoutManager(this));
        final SwipeRefreshLayout swipeRefreshLayoutSakit = (SwipeRefreshLayout) findViewById(R.id.swipeLayoutSakit);

        new DownloaderSakit(ListSakit.this, urlAddress, rv, swipeRefreshLayoutSakit).execute();

        swipeRefreshLayoutSakit.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new DownloaderSakit(ListSakit.this, urlAddress, rv, swipeRefreshLayoutSakit).execute();

            }
        });
    }
}