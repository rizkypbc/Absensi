package skripsi.rizky.absensi.model;

/**
 * Created by ASUS on 13/07/2017.
 */

public class Dosen extends BaseResponse {

    private DosenData dosenData;

    public Dosen(){

    }

    public DosenData getDosenData() {
        return dosenData;
    }

    public void setDosenData(DosenData dosenData) {
        this.dosenData = dosenData;
    }


}
