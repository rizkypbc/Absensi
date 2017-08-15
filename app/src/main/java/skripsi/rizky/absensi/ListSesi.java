package skripsi.rizky.absensi;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import skripsi.rizky.absensi.sesi.DownloaderSesi;

/**
 * Created by ASUS on 15/08/2017.
 */

public class ListSesi extends AppCompatActivity {

    static String urlAddress = "http://192.168.43.212/absensi/MataKuliah/viewSesi.php";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);


        final RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);

        new DownloaderSesi(ListSesi.this, urlAddress, rv, swipeRefreshLayout).execute();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new DownloaderSesi(ListSesi.this, urlAddress, rv, swipeRefreshLayout).execute();
            }
        });
    }
}
