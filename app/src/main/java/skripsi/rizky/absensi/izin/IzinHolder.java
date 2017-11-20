package skripsi.rizky.absensi.izin;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import skripsi.rizky.absensi.R;

/**
 * Created by ASUS on 19/09/2017.
 */

public class IzinHolder extends RecyclerView.ViewHolder {

    public TextView idSesiIzin;
    public TextView materiIzin;
    public TextView dosenIzin;
    public TextView pertemuanIzin;
    public TextView ruangIzin;
    public TextView tanggalIzin;
    public TextView waktuIzin;
    public TextView keteranganIzin;
    public TextView matkulIzin;


    public IzinHolder(View itemViewIzin) {
        super(itemViewIzin);

        idSesiIzin = (TextView) itemViewIzin.findViewById(R.id.textIdSesi);
        materiIzin = (TextView) itemViewIzin.findViewById(R.id.textMateri);
        dosenIzin = (TextView) itemViewIzin.findViewById(R.id.textNamaDosen);
        pertemuanIzin = (TextView) itemViewIzin.findViewById(R.id.textPertemuanDosen);
        ruangIzin = (TextView) itemViewIzin.findViewById(R.id.textKelasDosen);
        tanggalIzin = (TextView) itemViewIzin.findViewById(R.id.textTanggal);
        waktuIzin = (TextView) itemViewIzin.findViewById(R.id.textWaktu);
        keteranganIzin = (TextView) itemViewIzin.findViewById(R.id.textKeterangan);
        matkulIzin = (TextView) itemViewIzin.findViewById(R.id.textMatkul);
    }
}
