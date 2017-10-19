package skripsi.rizky.absensi.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import skripsi.rizky.absensi.HalamanVerifikasi;
import skripsi.rizky.absensi.R;
import skripsi.rizky.absensi.model.Verifikasi;
import skripsi.rizky.absensi.model.VerifikasiData;
import skripsi.rizky.absensi.verifikasi.VerifikasiHolder;

/**
 * Created by ASUS on 20/10/2017.
 */

public class ListVerifikasiAdapter extends RecyclerView.Adapter<VerifikasiHolder> {

    Context context;
    ArrayList<VerifikasiData> verifikasiDatas;

    public ListVerifikasiAdapter(Context context, ArrayList<VerifikasiData> verifikasiDatas) {
        this.context = context;
        this.verifikasiDatas = verifikasiDatas;
    }

    @Override
    public VerifikasiHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.content_verifikasi_absen, parent, false);
        return new VerifikasiHolder(view);
    }

    @Override
    public void onBindViewHolder(VerifikasiHolder holder, int position) {

        final VerifikasiData verifikasi = verifikasiDatas.get(position);

        holder.verifikasiId.setText(verifikasi.getId_sesi());
        holder.verifikasiNim.setText(verifikasi.getNim());
        holder.verifikasiNama.setText(verifikasi.getNama());
        holder.verifikasiJurusan.setText(verifikasi.getJurusan());
        holder.verifikasiMatakuliah.setText(verifikasi.getKode_matkul());
        holder.verifikasiTanggal.setText(verifikasi.getTanggal_absen());
        holder.verifikasiWaktu.setText(verifikasi.getWaktu_absen());
        holder.verifikasiKeterangan.setText(verifikasi.getKeterangan_absen());
        holder.verifikasiToken.setText(verifikasi.getToken_absen());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in = new Intent(context, HalamanVerifikasi.class);
                in.putExtra("verifikasi", verifikasi);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(in);
            }
        });

    }

    @Override
    public int getItemCount() {
        return verifikasiDatas.size();
    }
}