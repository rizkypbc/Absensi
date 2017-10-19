package skripsi.rizky.absensi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import skripsi.rizky.absensi.model.BaseResponse;
import skripsi.rizky.absensi.model.VerifikasiData;
import skripsi.rizky.absensi.network.VerifikasiAbsenService;

public class HalamanVerifikasi extends AppCompatActivity {

    private EditText textNIMEdit, textNamaEdit, textJurusanEdit, textMatakuliahEdit,
            textTanggalEdit, textWaktuEdit, textKeteranganEdit, textIdVerifikasiEdit;
    private Spinner spnToken;
    private Button btnVerifikasi;
    private VerifikasiAbsenService verifikasiAbsenService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_verifikasi);

        textNIMEdit = (EditText) findViewById(R.id.textNIMVerifikasiEdit);
        textNamaEdit = (EditText) findViewById(R.id.textNamaVerifikasiEdit);
        textJurusanEdit = (EditText) findViewById(R.id.textJurusanVerifikasiEdit);
        textTanggalEdit = (EditText) findViewById(R.id.textTanggalVerifikasiEdit);
        textWaktuEdit = (EditText) findViewById(R.id.textWaktuVerifikasiEdit);
        textMatakuliahEdit = (EditText) findViewById(R.id.textMatakuliahVerifikasiEdit);
        textKeteranganEdit = (EditText) findViewById(R.id.textKeteranganVerifikasiEdit);
        textIdVerifikasiEdit = (EditText) findViewById(R.id.textIdVerifikasiEdit);

        btnVerifikasi = (Button) findViewById(R.id.btnVerifikasiAbsenEdit);

        spnToken = (Spinner) findViewById(R.id.spnStatusVerifikasiEdit);

        textIdVerifikasiEdit.setEnabled(false);
        textNIMEdit.setEnabled(false);
        textNamaEdit.setEnabled(false);
        textJurusanEdit.setEnabled(false);
        textTanggalEdit.setEnabled(false);
        textWaktuEdit.setEnabled(false);
        textMatakuliahEdit.setEnabled(false);
        textKeteranganEdit.setEnabled(false);

        textIdVerifikasiEdit.setVisibility(View.INVISIBLE);

        if (getIntent().getSerializableExtra("verifikasi") != null) {

            VerifikasiData verifikasiData = (VerifikasiData) getIntent().getSerializableExtra("verifikasi");

            textIdVerifikasiEdit.setText(verifikasiData.getId());
            textNIMEdit.setText(verifikasiData.getNim());
            textNamaEdit.setText(verifikasiData.getNama());
            textJurusanEdit.setText(verifikasiData.getJurusan());
            textMatakuliahEdit.setText(verifikasiData.getKode_matkul());
            textTanggalEdit.setText(verifikasiData.getTanggal_absen());
            textWaktuEdit.setText(verifikasiData.getWaktu_absen());
            textKeteranganEdit.setText(verifikasiData.getKeterangan_absen());

//            Dosen dosen = PrefUtilDosen.getDosen(this, PrefUtilDosen.DOSEN_SESSION);

            btnVerifikasi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    String verifikasi = textIdVerifikasiEdit.getText().toString();
                    String token = spnToken.getSelectedItem().toString();

                    verifikasiAbsenService = new VerifikasiAbsenService(getApplicationContext());
                    verifikasiAbsenService.doVerifikasiAbsen(verifikasi, token, new Callback() {
                        @Override
                        public void onResponse(Call call, Response response) {

                            BaseResponse baseResponse = (BaseResponse) response.body();
                            if (baseResponse != null) {
                                if (!baseResponse.isError()) {

                                    HalamanLiatSesi.start(HalamanVerifikasi.this);
                                    HalamanVerifikasi.this.finish();
                                }

                                Toast.makeText(HalamanVerifikasi.this, baseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {

                            Toast.makeText(HalamanVerifikasi.this, "An Error Occured", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
    }
}

