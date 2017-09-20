package skripsi.rizky.absensi.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import skripsi.rizky.absensi.IzinMahasiswa;
import skripsi.rizky.absensi.R;
import skripsi.rizky.absensi.izin.IzinHolder;
import skripsi.rizky.absensi.model.IzinData;

/**
 * Created by ASUS on 19/09/2017.
 */

public class ListIzinAdapter extends RecyclerView.Adapter<IzinHolder> {

    Context context;
    ArrayList<IzinData> izinDatas;

    public ListIzinAdapter(Context context, ArrayList<IzinData> izinDatas) {
        this.context = context;
        this.izinDatas = izinDatas;
    }

    @Override
    public IzinHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.content_list_izin, parent, false);
        return new IzinHolder(view);
    }

    @Override
    public void onBindViewHolder(IzinHolder holder, int position) {

        final IzinData izin = izinDatas.get(position);

        holder.idSesiIzin.setText(izin.getId_sesi());
        holder.materiIzin.setText(izin.getMateri());
        holder.dosenIzin.setText(izin.getNama_dosen());
        holder.pertemuanIzin.setText(izin.getPertemuan());
        holder.ruangIzin.setText(izin.getRuang());
        holder.tanggalIzin.setText(izin.getTanggal());
        holder.waktuIzin.setText(izin.getWaktu());
        holder.keteranganIzin.setText(izin.getKeterangan());
        holder.matkulIzin.setText(izin.getKode_matkul());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, IzinMahasiswa.class);
                intent.putExtra("izin", izin);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return izinDatas.size();
    }
}
