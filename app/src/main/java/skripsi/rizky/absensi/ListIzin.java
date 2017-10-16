package skripsi.rizky.absensi;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import skripsi.rizky.absensi.izin.DownloaderIzin;

public class ListIzin extends AppCompatActivity {

    static String urlAddress = "http://192.168.43.212/absensi/MataKuliah/viewSesiMahasiswa.php";
//    static String urlAddress = "http://aksesblk-samarinda.com/absensi/MataKuliah/viewSesiMahasiswa.php";

    public static void start(Context context) {

        Intent intent = new Intent(context, ListIzin.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_izin);

        final RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerViewIzin);
        rv.setLayoutManager(new LinearLayoutManager(this));
        final SwipeRefreshLayout swipeRefreshLayoutIzin = (SwipeRefreshLayout) findViewById(R.id.swipeLayoutIzin);

        new DownloaderIzin(ListIzin.this, urlAddress, rv, swipeRefreshLayoutIzin).execute();

        swipeRefreshLayoutIzin.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new DownloaderIzin(ListIzin.this, urlAddress, rv, swipeRefreshLayoutIzin).execute();
            }
        });
    }
}
