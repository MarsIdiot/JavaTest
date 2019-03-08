package collection.map;

import java.io.Serializable;

/**
 * @Description:
 * @Date: 2019/3/8 16:48
 */
public class Hotel implements Serializable {

    private  String hotelId;
    private String number;

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
