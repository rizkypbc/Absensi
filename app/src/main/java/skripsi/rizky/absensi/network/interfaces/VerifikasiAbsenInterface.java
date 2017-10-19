package skripsi.rizky.absensi.network.interfaces;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import skripsi.rizky.absensi.model.BaseResponse;
import skripsi.rizky.absensi.network.config.Config;

/**
 * Created by ASUS on 20/10/2017.
 */

public interface VerifikasiAbsenInterface {

    @FormUrlEncoded
    @POST(Config.API_VERIFIKASI_ABSEN)
    Call<BaseResponse> verifikasiAbsen(
            @Field("id") String id,
            @Field("token_absen") String token_absen
    );
}
