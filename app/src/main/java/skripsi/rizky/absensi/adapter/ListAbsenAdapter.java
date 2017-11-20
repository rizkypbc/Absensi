package skripsi.rizky.absensi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import skripsi.rizky.absensi.R;
import skripsi.rizky.absensi.absen.SiswaHolder;
import skripsi.rizky.absensi.model.AbsenData;

/**
 * Created by ASUS on 19/08/2017.
 */

public class ListAbsenAdapter extends RecyclerView.Adapter<SiswaHolder> {

    Context context;
    ArrayList<AbsenData> absenDatas;

    public ListAbsenAdapter(Context context, ArrayList<AbsenData> absenDatas) {
        this.context = context;
        this.absenDatas = absenDatas;
    }

    @Override
    public SiswaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_absen, parent, false);
        return new SiswaHolder(view);
    }

    @Override
    public void onBindViewHolder(SiswaHolder holder, int position) {

        final AbsenData absen = absenDatas.get(position);

//        holder.absenKodeSesi.setText(absen.getId_sesi());
        holder.absenNim.setText(absen.getNim());
        holder.absenNama.setText(absen.getNama());
        holder.absenJurusan.setText(absen.getJurusan());
        holder.absenMataKuliah.setText(absen.getKode_matkul());
        holder.absenTanggal.setText(absen.getTanggal_absen());
        holder.absenWaktu.setText(absen.getWaktu_absen());
        holder.absenKeterangan.setText(absen.getKeterangan_absen());
    }

    @Override
    public int getItemCount() {
        return absenDatas.size();
    }
}
