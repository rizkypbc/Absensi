package skripsi.rizky.absensi.model;

import java.util.List;

/**
 * Created by ASUS on 10/08/2017.
 */

public class Value {

    String value;
    String message;
    List<SesiData> result;

    public String getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

    public List<SesiData> getResult() {
        return result;
    }
}
