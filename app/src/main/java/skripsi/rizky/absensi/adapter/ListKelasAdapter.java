package skripsi.rizky.absensi.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import skripsi.rizky.absensi.AbsenMahasiswaActivity;
import skripsi.rizky.absensi.R;
import skripsi.rizky.absensi.UpdateSesi;
import skripsi.rizky.absensi.kelas.KelasHolder;
import skripsi.rizky.absensi.model.SesiData;
import skripsi.rizky.absensi.sesi.MyViewHolder;

/**
 * Created by ASUS on 15/08/2017.
 */

public class ListKelasAdapter extends RecyclerView.Adapter<KelasHolder> {

    Context context;
    ArrayList<SesiData> sesiDatas;

    public ListKelasAdapter(Context context, ArrayList<SesiData> sesiDatas) {

        this.context = context;
        this.sesiDatas = sesiDatas;
    }


    @Override
    public KelasHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_kelas, parent, false);
        return new KelasHolder(v);
    }

    @Override
    public void onBindViewHolder(KelasHolder holder, int position) {

        final SesiData kelas = sesiDatas.get(position);

        holder.idSesiKelas.setText(kelas.getId_sesi());
        holder.materiKelas.setText(kelas.getMateri());
        holder.dosenKelas.setText(kelas.getNama_dosen());
        holder.pertemuanKelas.setText(kelas.getPertemuan());
        holder.ruangKelas.setText(kelas.getRuang());
        holder.tanggalKelas.setText(kelas.getTanggal());
        holder.waktuKelas.setText(kelas.getWaktu());
        holder.keteranganKelas.setText(kelas.getKeterangan());
        holder.matkulKelas.setText(kelas.getKode_matkul());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context, AbsenMahasiswaActivity.class);
                in.putExtra("kelas", kelas);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(in);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sesiDatas.size();
    }
}
