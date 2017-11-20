package skripsi.rizky.absensi.network.interfaces;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import skripsi.rizky.absensi.model.BaseResponse;
import skripsi.rizky.absensi.network.config.Config;

/**
 * Created by ASUS on 14/09/2017.
 */

public interface LiatSesiInterface {

    @FormUrlEncoded
    @POST(Config.API_LIAT_SESI)
    Call<BaseResponse> getLiatSesi(
            @Field("nama_dosen") String nama_dosen
    );
}
