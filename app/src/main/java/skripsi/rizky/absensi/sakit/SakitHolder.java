package skripsi.rizky.absensi.sakit;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import skripsi.rizky.absensi.R;

/**
 * Created by ASUS on 16/10/2017.
 */

public class SakitHolder extends RecyclerView.ViewHolder {

    public TextView idSesiSakit;
    public TextView materiSakit;
    public TextView dosenSakit;
    public TextView pertemuanSakit;
    public TextView ruangSakit;
    public TextView tanggalSakit;
    public TextView waktuSakit;
    public TextView keteranganSakit;
    public TextView matkulSakit;

    public SakitHolder(View itemViewSakit) {
        super(itemViewSakit);

        idSesiSakit = (TextView) itemViewSakit.findViewById(R.id.textIdSesiSakit);
        materiSakit = (TextView) itemViewSakit.findViewById(R.id.textMateriSakit);
        dosenSakit = (TextView) itemViewSakit.findViewById(R.id.textNamaDosenSakit);
        pertemuanSakit = (TextView) itemViewSakit.findViewById(R.id.textPertemuanDosenSakit);
        ruangSakit = (TextView) itemViewSakit.findViewById(R.id.textKelasDosenSakit);
        tanggalSakit = (TextView) itemViewSakit.findViewById(R.id.textTanggalSakit);
        waktuSakit = (TextView) itemViewSakit.findViewById(R.id.textWaktuSakit);
        keteranganSakit = (TextView) itemViewSakit.findViewById(R.id.textKeteranganSakit);
        matkulSakit = (TextView) itemViewSakit.findViewById(R.id.textMatkulSakit);
    }
}
