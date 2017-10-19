package skripsi.rizky.absensi.verifikasi;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import skripsi.rizky.absensi.R;

/**
 * Created by ASUS on 20/10/2017.
 */

public class VerifikasiHolder extends RecyclerView.ViewHolder {

    public TextView verifikasiId;
    public TextView verifikasiNim;
    public TextView verifikasiNama;
    public TextView verifikasiJurusan;
    public TextView verifikasiTanggal;
    public TextView verifikasiWaktu;
    public TextView verifikasiMatakuliah;
    public TextView verifikasiKeterangan;
    public TextView verifikasiToken;

    public VerifikasiHolder(View itemViewVerifikasi) {
        super(itemViewVerifikasi);

        verifikasiId = (TextView) itemViewVerifikasi.findViewById(R.id.textIdAbsenVerifikasi);
        verifikasiNim = (TextView) itemViewVerifikasi.findViewById(R.id.txtNIMAbsenVerifikasi);
        verifikasiNama = (TextView) itemViewVerifikasi.findViewById(R.id.txtNamaAbsenVerifikasi);
        verifikasiJurusan = (TextView) itemViewVerifikasi.findViewById(R.id.txtJurusanAbsenVerifikasi);
        verifikasiTanggal = (TextView) itemViewVerifikasi.findViewById(R.id.txtTanggalAbsenVerifikasi);
        verifikasiWaktu = (TextView) itemViewVerifikasi.findViewById(R.id.txtWaktuAbsenVerifikasi);
        verifikasiMatakuliah = (TextView) itemViewVerifikasi.findViewById(R.id.txtMataKuliahAbsenVerifikasi);
        verifikasiKeterangan = (TextView) itemViewVerifikasi.findViewById(R.id.txtKeteranganAbsenVerifikasi);
        verifikasiToken = (TextView) itemViewVerifikasi.findViewById(R.id.txtTokenAbsenVerifikasi);
    }
}

