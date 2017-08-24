package skripsi.rizky.absensi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import skripsi.rizky.absensi.matakuliah.Downloader;
import skripsi.rizky.absensi.model.BaseResponse;
import skripsi.rizky.absensi.model.Dosen;
import skripsi.rizky.absensi.model.SesiData;
import skripsi.rizky.absensi.network.TutupSesiService;
import skripsi.rizky.absensi.util.PrefUtilDosen;

public class UpdateSesi extends AppCompatActivity {

//    final static String API_MATAKULIAH = "http://192.168.43.212/absensi/MataKuliah/mataKuliah.php";

//    final static String API_MATAKULIAH = "http://10.223.222.40/absensi/MataKuliah/mataKuliah.php";

    EditText textMateriEdit, textNamaDosenEdit, textTanggalEdit,
            textWaktuEdit, textIdSesi, textKelasEdit, textMatkulEdit, textStatusEdit;

    private Spinner spnStatusEdit;

    private Button btnTutupSesi;
    private TutupSesiService tutupSesiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_sesi);

        textMateriEdit = (EditText) findViewById(R.id.textMateriEdit);
        textNamaDosenEdit = (EditText) findViewById(R.id.textNamaDosenEdit);
        textTanggalEdit = (EditText) findViewById(R.id.textTanggalEdit);
        textWaktuEdit = (EditText) findViewById(R.id.textWaktuEdit);
        textMatkulEdit = (EditText) findViewById(R.id.textMatKulEdit);
        spnStatusEdit = (Spinner) findViewById(R.id.spnStatusEdit);
        btnTutupSesi = (Button) findViewById(R.id.btnTutupSesi);
        textIdSesi = (EditText) findViewById(R.id.editTextIdSesi);
        textKelasEdit = (EditText) findViewById(R.id.textKelasEdit);

        textIdSesi.setEnabled(false);
        textNamaDosenEdit.setEnabled(false);
        textKelasEdit.setEnabled(false);
        textTanggalEdit.setEnabled(false);
        textWaktuEdit.setEnabled(false);
        textMatkulEdit.setEnabled(false);
        btnTutupSesi.setVisibility(View.INVISIBLE);


        if (getIntent().getSerializableExtra("data") != null) {

            SesiData sesiData = (SesiData) getIntent().getSerializableExtra("data");
            textIdSesi.setText(sesiData.getId_sesi());
            textMateriEdit.setText(sesiData.getMateri());
            textNamaDosenEdit.setText(sesiData.getNama_dosen());
            textKelasEdit.setText(sesiData.getRuang());
            textTanggalEdit.setText(sesiData.getTanggal());
            textWaktuEdit.setText(sesiData.getWaktu());
            textMatkulEdit.setText(sesiData.getKode_matkul());
        }

        Dosen dosen = PrefUtilDosen.getDosen(this, PrefUtilDosen.DOSEN_SESSION);
        SesiData sesiData = (SesiData) getIntent().getSerializableExtra("data");

        if (dosen.getDosenData().getNama().equalsIgnoreCase(sesiData.getNama_dosen())) {

            btnTutupSesi.setVisibility(View.VISIBLE);
            btnTutupSesi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String materi = textMateriEdit.getText().toString();
                    if (TextUtils.isEmpty(materi)) {
                        textMateriEdit.setError("Materi tidak boleh kosong");
                        return;
                    }

                    String sesi = textIdSesi.getText().toString();

                    String keterangan = spnStatusEdit.getSelectedItem().toString();

                    tutupSesiService = new TutupSesiService(getApplicationContext());
                    tutupSesiService.doTutupSesi(sesi, materi, keterangan, new Callback() {
                        @Override
                        public void onResponse(Call call, Response response) {

                            BaseResponse baseResponse = (BaseResponse) response.body();
                            if (baseResponse != null) {
                                if (!baseResponse.isError()) {

                                    DosenActivity.start(UpdateSesi.this);
                                    UpdateSesi.this.finish();
                                }

                                Toast.makeText(UpdateSesi.this, baseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {

                            Toast.makeText(UpdateSesi.this, "An error occured", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        } else {
            btnTutupSesi.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
