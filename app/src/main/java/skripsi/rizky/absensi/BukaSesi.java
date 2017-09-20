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
import skripsi.rizky.absensi.ruang.DownloaderRuang;
import skripsi.rizky.absensi.util.PrefUtilDosen;

public class BukaSesi extends AppCompatActivity {

//    final static String API_MATAKULIAH = "http://192.168.1.14/absensi/MataKuliah/mataKuliah.php";
//    final static String API_RUANG = "http://192.168.1.14/absensi/MataKuliah/ruang.php";

    final static String API_MATAKULIAH = "http://aksesblk-samarinda.com/absensi/MataKuliah/mataKuliah.php";
    final static String API_RUANG = "http://aksesblk-samarinda.com/absensi/MataKuliah/ruang.php";

    private BukaSesiService bukaSesiService;
    private Spinner spnMatkul, spnStatus, spnKelas;
    private Button btnBukaKelas;
    private EditText txtMateri, txtPertemuanSesi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buka_sesi);

        spnStatus = (Spinner) findViewById(R.id.spnStatus);
        spnMatkul = (Spinner) findViewById(R.id.spnMatKul);
        btnBukaKelas = (Button) findViewById(R.id.btnBukaSesi);
        txtMateri = (EditText) findViewById(R.id.txtMateri);
        txtPertemuanSesi = (EditText) findViewById(R.id.txtPertemuanSesi);
        spnKelas = (Spinner) findViewById(R.id.spnKelas);

        btnBukaKelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pertemuanSesi = txtPertemuanSesi.getText().toString();

                if (TextUtils.isEmpty(pertemuanSesi)) {
                    txtPertemuanSesi.setError("Field Pertemuan tidak boleh kosong");
                    return;
                }

                String materi = txtMateri.getText().toString();

                if (TextUtils.isEmpty(materi)) {
                    txtMateri.setError("Field Materi tidak boleh kosong");
                    return;
                }

                Dosen dosen = PrefUtilDosen.getDosen(getApplicationContext(), PrefUtilDosen.DOSEN_SESSION);
                String namaDosen = dosen.getDosenData().getNama();


                String ruang = spnKelas.getSelectedItem().toString();
                String matkul = spnMatkul.getSelectedItem().toString();
                String keterangan = spnStatus.getSelectedItem().toString();

                bukaSesiService = new BukaSesiService(getApplicationContext());
                bukaSesiService.doBukaSesi(materi, namaDosen, pertemuanSesi, ruang, keterangan, matkul, new Callback() {
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
        new DownloaderRuang(BukaSesi.this, API_RUANG, spnKelas).execute();
    }
}
