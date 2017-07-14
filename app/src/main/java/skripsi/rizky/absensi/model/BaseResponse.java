package skripsi.rizky.absensi.model;

/**
 * Created by ASUS on 12/07/2017.
 */

public class BaseResponse {

    private boolean error;
    private String message;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
