package skripsi.rizky.absensi.network.interfaces;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import skripsi.rizky.absensi.model.BaseResponse;
import skripsi.rizky.absensi.network.config.Config;

/**
 * Created by ASUS on 15/10/2017.
 */

public interface BukaSesiTanpaLokasiInterface {

    @FormUrlEncoded
    @POST(Config.API_BUKA_SESI_TANPA_LOKASI)
    Call<BaseResponse> getBukaSesiTanpaLokasi(
//            @Field("id_sesi") String id_sesi,
            @Field("materi") String materi,
            @Field("nama_dosen") String nama_dosen,
            @Field("pertemuan") String pertemuan,
            @Field("ruang") String ruang,
            @Field("keterangan") String keterangan,
            @Field("kode_matkul") String kode_matkul);
}
