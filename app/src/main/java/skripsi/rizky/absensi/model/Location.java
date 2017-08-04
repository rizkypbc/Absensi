package skripsi.rizky.absensi.model;

/**
 * Created by ASUS on 04/08/2017.
 */

public class Location extends BaseResponse {

    private LocationData locationData;

    public Location() {

    }

    public LocationData getLocationData() {
        return locationData;
    }

    public void setLocationData(LocationData locationData) {
        this.locationData = locationData;
    }

}
