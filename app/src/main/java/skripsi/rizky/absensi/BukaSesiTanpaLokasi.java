package skripsi.rizky.absensi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
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
import skripsi.rizky.absensi.network.BukaSesiTanpaLokasiService;
import skripsi.rizky.absensi.ruang.DownloaderRuang;
import skripsi.rizky.absensi.util.PrefUtilDosen;

public class BukaSesiTanpaLokasi extends AppCompatActivity {

    final static String API_MATAKULIAH = "http://192.168.43.212/absensi/MataKuliah/mataKuliah.php";
    final static String API_RUANG = "http://192.168.43.212/absensi/MataKuliah/ruang.php";

    private BukaSesiTanpaLokasiService bukaSesiTanpaLokasiService;
    private Spinner spnMatkul, spnStatus, spnKelas;
    private Button btnBukaKelas;
    private EditText txtMateri, txtPertemuanKelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buka_sesi_tanpa_lokasi);

        txtMateri = (EditText) findViewById(R.id.txtMateriTanpaLokasi);
        txtPertemuanKelas = (EditText) findViewById(R.id.txtPertemuanSesiTanpaLokasi);

        spnMatkul = (Spinner) findViewById(R.id.spnMatKulTanpaLokasi);
        spnStatus = (Spinner) findViewById(R.id.spnStatusTanpaLokasi);
        spnKelas = (Spinner) findViewById(R.id.spnKelasTanpaLokasi);

        btnBukaKelas = (Button) findViewById(R.id.btnBukaSesiTanpaLokasi);

        btnBukaKelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pertemuanSesi = txtPertemuanKelas.getText().toString();

                if (TextUtils.isEmpty(pertemuanSesi)) {
                    txtPertemuanKelas.setError("Field Pertemuan tidak boleh kosong");
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

                bukaSesiTanpaLokasiService = new BukaSesiTanpaLokasiService(getApplicationContext());
                bukaSesiTanpaLokasiService.doBukaSesiTanpaLokasi(materi, namaDosen, pertemuanSesi, ruang, keterangan, matkul, new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {

                        BaseResponse baseResponse = (BaseResponse) response.body();

                        if (baseResponse != null) {
                            if (!baseResponse.isError()) {

                                DosenActivity.start(BukaSesiTanpaLokasi.this);
                                BukaSesiTanpaLokasi.this.finish();
                            }

                            Toast.makeText(BukaSesiTanpaLokasi.this, baseResponse.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {

                        Toast.makeText(BukaSesiTanpaLokasi.this, "An error occured", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        new Downloader(BukaSesiTanpaLokasi.this, API_MATAKULIAH, spnMatkul).execute();
        new DownloaderRuang(BukaSesiTanpaLokasi.this, API_RUANG, spnKelas).execute();
    }
}
