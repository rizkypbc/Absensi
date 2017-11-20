package skripsi.rizky.absensi.model;

/**
 * Created by ASUS on 16/10/2017.
 */

public class Sakit extends BaseResponse {

    private SakitData sakitData;

    public Sakit() {

    }

    public SakitData getSakitData() {
        return sakitData;
    }

    public void setSakitData(SakitData sakitData) {
        this.sakitData = sakitData;
    }
}

