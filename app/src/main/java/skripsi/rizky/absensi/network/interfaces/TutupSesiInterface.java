package skripsi.rizky.absensi.network.interfaces;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import skripsi.rizky.absensi.model.BaseResponse;
import skripsi.rizky.absensi.network.config.Config;

/**
 * Created by ASUS on 12/08/2017.
 */

public interface TutupSesiInterface {

    @FormUrlEncoded
    @POST(Config.API_TUTUP_SESI)
    Call<BaseResponse> tutupSesi(
            @Field("id_sesi") String id_sesi,
            @Field("materi") String materi,
            @Field("keterangan") String keterangan
    );
}
