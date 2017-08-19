package skripsi.rizky.absensi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import skripsi.rizky.absensi.matakuliah.Downloader;
import skripsi.rizky.absensi.model.BaseResponse;
import skripsi.rizky.absensi.model.Dosen;
import skripsi.rizky.absensi.network.BukaSesiService;
import skripsi.rizky.absensi.util.PrefUtilDosen;

public class BukaSesi extends AppCompatActivity {

    final static String API_MATAKULIAH = "http://192.168.43.212/absensi/MataKuliah/mataKuliah.php";

//    final static String API_MATAKULIAH = "http:// 10.223.217.154/absensi/MataKuliah/mataKuliah.php";

    private BukaSesiService bukaSesiService;
    private Spinner spnMatkul, spnStatus;
    private Button btnBukaKelas;
    private EditText txtMateri, txtIdSesi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buka_sesi);

        spnStatus = (Spinner) findViewById(R.id.spnStatus);
        spnMatkul = (Spinner) findViewById(R.id.spnMatKul);
        btnBukaKelas = (Button) findViewById(R.id.btnBukaSesi);
        txtMateri = (EditText) findViewById(R.id.txtMateri);
        txtIdSesi = (EditText) findViewById(R.id.txtIdSesi);

        btnBukaKelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String idSesi = txtIdSesi.getText().toString();

                if (TextUtils.isEmpty(idSesi)) {
                    txtIdSesi.setError("Kode Sesi tidak boleh kosong");
                    return;
                }

                String materi = txtMateri.getText().toString();

                if (TextUtils.isEmpty(materi)) {
                    txtMateri.setError("Materi tidak boleh kosong");
                    return;
                }

                Dosen dosen = PrefUtilDosen.getDosen(getApplicationContext(), PrefUtilDosen.DOSEN_SESSION);
                String namaDosen = dosen.getDosenData().getNama();


                String matkul = spnMatkul.getSelectedItem().toString();
                String keterangan = spnStatus.getSelectedItem().toString();

                bukaSesiService = new BukaSesiService(getApplicationContext());
                bukaSesiService.doBukaSesi(idSesi, materi, namaDosen, keterangan, matkul, new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {

                        BaseResponse baseResponse = (BaseResponse) response.body();

                        if (baseResponse != null) {
                            if (!baseResponse.isError()) {

                                DosenActivity.start(BukaSesi.this);
                                BukaSesi.this.finish();
                            }

                            Toast.makeText(BukaSesi.this, baseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {

                        Toast.makeText(BukaSesi.this, "An error occured", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        new Downloader(BukaSesi.this, API_MATAKULIAH, spnMatkul).execute();
    }
}
