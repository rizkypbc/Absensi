package skripsi.rizky.absensi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import butterknife.OnClick;
import skripsi.rizky.absensi.model.Dosen;
import skripsi.rizky.absensi.util.PrefUtilDosen;

public class DosenActivity extends AppCompatActivity {

    private TextView greetingDosen;
    private TextView nip;
    private ImageView btnLogoutDosen, btnSesi;
    private Button btnListSesi;


    public static void start(Context context){

        Intent intent = new Intent(context, DosenActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen);

        greetingDosen = (TextView)findViewById(R.id.greetingDosen);
        nip = (TextView)findViewById(R.id.nip);
        btnLogoutDosen = (ImageView) findViewById(R.id.btn_logout_dosen);
        btnSesi = (ImageView) findViewById(R.id.btn_sesi);
        btnListSesi = (Button) findViewById(R.id.btn_list_sesi);


        Dosen dosen = PrefUtilDosen.getDosen(this, PrefUtilDosen.DOSEN_SESSION);

        greetingDosen.setText(getResources().getString(R.string.greeting, dosen.getDosenData().getNama()));
        nip.setText(dosen.getDosenData().getNip());

        btnListSesi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(DosenActivity.this, ListSesi.class));
            }
        });


        btnLogoutDosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                logoutActDosen();

                LoginDosenActivity.start(DosenActivity.this);
                DosenActivity.this.finish();
            }
        });

        btnSesi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent sesi = new Intent(getApplicationContext(), BukaSesi.class);
                startActivity(sesi);
            }
        });
    }


    void logoutActDosen(){

        PrefUtilDosen.clearDosen(this);
    }
}
