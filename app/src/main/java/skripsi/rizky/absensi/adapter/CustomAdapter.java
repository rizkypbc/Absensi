package skripsi.rizky.absensi.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import skripsi.rizky.absensi.R;
import skripsi.rizky.absensi.sesi.MyViewHolder;
import skripsi.rizky.absensi.UpdateSesi;
import skripsi.rizky.absensi.model.SesiData;

/**
 * Created by ASUS on 14/08/2017.
 */

public class CustomAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    ArrayList<SesiData> sesiDatas;

    public CustomAdapter(Context context, ArrayList<SesiData> sesiDatas) {

        this.context = context;
        this.sesiDatas = sesiDatas;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_view, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final SesiData data = sesiDatas.get(position);

        holder.id_sesi.setText(data.getId_sesi());
        holder.materi.setText(data.getMateri());
        holder.dosen.setText(data.getNama_dosen());
        holder.pertemuan.setText(data.getPertemuan());
        holder.ruang.setText(data.getRuang());
        holder.tanggal.setText(data.getTanggal());
        holder.waktu.setText(data.getWaktu());
        holder.keterangan.setText(data.getKeterangan());
        holder.matkul.setText(data.getKode_matkul());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context, UpdateSesi.class);
                in.putExtra("data", data);
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
