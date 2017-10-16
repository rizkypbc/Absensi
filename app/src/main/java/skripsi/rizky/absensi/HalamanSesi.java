package skripsi.rizky.absensi;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class HalamanSesi extends AppCompatActivity {

    private ImageView btnBukaSesiLokasi, btnBukaSesiTanpaLokasi;

    public static void start(Context context) {

        Intent intent = new Intent(context, DosenActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_sesi);

        btnBukaSesiLokasi = (ImageView) findViewById(R.id.btnBukaSesiLokasi);
        btnBukaSesiTanpaLokasi = (ImageView) findViewById(R.id.btnBukaTanpaLokasi);


        btnBukaSesiLokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HalamanSesi.this, BukaSesi.class));
            }
        });

        btnBukaSesiTanpaLokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HalamanSesi.this, BukaSesiTanpaLokasi.class));
            }
        });
    }
}
