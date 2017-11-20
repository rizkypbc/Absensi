package skripsi.rizky.absensi.model;

/**
 * Created by ASUS on 19/09/2017.
 */

public class Izin extends BaseResponse {

    private IzinData izinData;

    public Izin() {

    }

    public IzinData getIzinData() {
        return izinData;
    }

    public void setIzinData(IzinData izinData) {
        this.izinData = izinData;
    }
}
