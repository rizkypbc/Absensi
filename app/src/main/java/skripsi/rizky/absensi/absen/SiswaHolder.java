package skripsi.rizky.absensi.absen;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import skripsi.rizky.absensi.R;

/**
 * Created by ASUS on 19/08/2017.
 */

public class SiswaHolder extends RecyclerView.ViewHolder {

    public TextView absenNim;
    public TextView absenNama;
    public TextView absenJurusan;
    public TextView absenTanggal;
    public TextView absenWaktu;
    public TextView absenMataKuliah;
    public TextView absenKeterangan;


    public SiswaHolder(View itemViewAbsen) {
        super(itemViewAbsen);

        absenNim = (TextView) itemViewAbsen.findViewById(R.id.txtNIMAbsen);
        absenNama = (TextView) itemViewAbsen.findViewById(R.id.txtNamaAbsen);
        absenJurusan = (TextView) itemViewAbsen.findViewById(R.id.txtJurusanAbsen);
        absenTanggal = (TextView) itemViewAbsen.findViewById(R.id.txtTanggalAbsen);
        absenWaktu = (TextView) itemViewAbsen.findViewById(R.id.txtWaktuAbsen);
        absenMataKuliah = (TextView) itemViewAbsen.findViewById(R.id.txtMataKuliahAbsen);
        absenKeterangan = (TextView) itemViewAbsen.findViewById(R.id.txtKeteranganAbsen);
    }
}
