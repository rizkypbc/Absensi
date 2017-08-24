package skripsi.rizky.absensi.kelas;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import skripsi.rizky.absensi.R;

/**
 * Created by ASUS on 15/08/2017.
 */

public class KelasHolder extends RecyclerView.ViewHolder {

    public TextView idSesiKelas;
    public TextView materiKelas;
    public TextView dosenKelas;
    public TextView ruangKelas;
    public TextView tanggalKelas;
    public TextView waktuKelas;
    public TextView keteranganKelas;
    public TextView matkulKelas;

    public KelasHolder(View itemViewKelas) {

        super(itemViewKelas);

        idSesiKelas = (TextView) itemViewKelas.findViewById(R.id.textIdSesiKelas);
        materiKelas = (TextView) itemViewKelas.findViewById(R.id.textMateriKelas);
        dosenKelas = (TextView) itemViewKelas.findViewById(R.id.textNamaDosenKelas);
        ruangKelas = (TextView) itemViewKelas.findViewById(R.id.textRuangKelas);
        tanggalKelas = (TextView) itemViewKelas.findViewById(R.id.textTanggalKelas);
        waktuKelas = (TextView) itemViewKelas.findViewById(R.id.textWaktuKelas);
        keteranganKelas = (TextView) itemViewKelas.findViewById(R.id.textKeteranganKelas);
        matkulKelas = (TextView) itemViewKelas.findViewById(R.id.textMatkulKelas);
    }
}
