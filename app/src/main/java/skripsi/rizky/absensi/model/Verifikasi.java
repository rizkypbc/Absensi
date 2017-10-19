package skripsi.rizky.absensi.model;

/**
 * Created by ASUS on 20/10/2017.
 */

public class Verifikasi extends BaseResponse {

    private VerifikasiData verifikasiData;


    public Verifikasi() {

    }

    public VerifikasiData getVerifikasiData() {
        return verifikasiData;
    }

    public void setVerifikasiData(VerifikasiData verifikasiData) {
        this.verifikasiData = verifikasiData;
    }

}

