package skripsi.rizky.absensi.sesi;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import skripsi.rizky.absensi.R;

/**
 * Created by ASUS on 14/08/2017.
 */

public class MyViewHolder extends RecyclerView.ViewHolder {

    public TextView id_sesi;
    public TextView materi;
    public TextView dosen;
    public TextView pertemuan;
    public TextView ruang;
    public TextView tanggal;
    public TextView waktu;
    public TextView keterangan;
    public TextView matkul;

    public MyViewHolder(View itemView) {

        super(itemView);

        id_sesi = (TextView) itemView.findViewById(R.id.textIdSesi);
        materi = (TextView) itemView.findViewById(R.id.textMateri);
        dosen = (TextView) itemView.findViewById(R.id.textNamaDosen);
        pertemuan = (TextView) itemView.findViewById(R.id.textPertemuanDosen);
        ruang = (TextView) itemView.findViewById(R.id.textKelasDosen);
        tanggal = (TextView) itemView.findViewById(R.id.textTanggal);
        waktu = (TextView) itemView.findViewById(R.id.textWaktu);
        keterangan = (TextView) itemView.findViewById(R.id.textKeterangan);
        matkul = (TextView) itemView.findViewById(R.id.textMatkul);
    }
}
