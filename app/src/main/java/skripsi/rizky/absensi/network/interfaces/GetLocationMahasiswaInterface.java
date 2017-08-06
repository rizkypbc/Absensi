package skripsi.rizky.absensi.network.interfaces;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import skripsi.rizky.absensi.model.BaseResponse;
import skripsi.rizky.absensi.network.config.Config;

/**
 * Created by ASUS on 04/08/2017.
 */

public interface GetLocationMahasiswaInterface {

    @FormUrlEncoded
    @POST(Config.API_GET_LOCATION)
    Call<BaseResponse> getLocationMahasiswa(
            @Field("nim") String nim,
            @Field("nama") String nama,
            @Field("jurusan") String jurusan,
            @Field("kelas") String kelas,
            @Field("latitude") double latitude,
            @Field("longitude") double longitude);
}
