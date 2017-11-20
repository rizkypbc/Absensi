package skripsi.rizky.absensi.model;

/**
 * Created by ASUS on 12/08/2017.
 */

public class Sesi extends BaseResponse {

    private SesiData sesiData;

    public Sesi() {

    }

    public SesiData getSesiData() {
        return sesiData;
    }

    public void setSesiData(SesiData sesiData) {
        this.sesiData = sesiData;
    }
}
