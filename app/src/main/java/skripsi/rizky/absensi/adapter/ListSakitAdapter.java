package skripsi.rizky.absensi.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import skripsi.rizky.absensi.HalamanSakit;
import skripsi.rizky.absensi.IzinMahasiswa;
import skripsi.rizky.absensi.R;
import skripsi.rizky.absensi.model.SakitData;
import skripsi.rizky.absensi.sakit.SakitHolder;

/**
 * Created by ASUS on 16/10/2017.
 */

public class ListSakitAdapter extends RecyclerView.Adapter<SakitHolder> {

    Context context;
    ArrayList<SakitData> sakitDatas;

    public ListSakitAdapter(Context context, ArrayList<SakitData> sakitDatas) {
        this.context = context;
        this.sakitDatas = sakitDatas;
    }

    @Override
    public SakitHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.content_list_sakit, parent, false);
        return new SakitHolder(view);
    }

    @Override
    public void onBindViewHolder(SakitHolder holder, int position) {

        final SakitData sakit = sakitDatas.get(position);

        holder.idSesiSakit.setText(sakit.getId_sesi());
        holder.materiSakit.setText(sakit.getMateri());
        holder.dosenSakit.setText(sakit.getNama_dosen());
        holder.pertemuanSakit.setText(sakit.getPertemuan());
        holder.ruangSakit.setText(sakit.getRuang());
        holder.tanggalSakit.setText(sakit.getTanggal());
        holder.waktuSakit.setText(sakit.getWaktu());
        holder.keteranganSakit.setText(sakit.getKeterangan());
        holder.matkulSakit.setText(sakit.getKode_matkul());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, HalamanSakit.class);
                intent.putExtra("sakit", sakit);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sakitDatas.size();
    }
}