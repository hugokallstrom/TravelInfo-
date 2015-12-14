package rss_items;

/**
 * Created by hugo on 4/7/15.
 * Class holding information about the offers hotel.
 */
public class HotelInfo {

    private String hotelName;
    private String hotelImage;
    private String hotelGrade;
    private String roomDescription;
    private String hotelGradestring;

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelImage() {
        return hotelImage;
    }

    public void setHotelImage(String hotelImage) {
        this.hotelImage = hotelImage;
    }

    public String getHotelGrade() {
        return hotelGrade;
    }

    public void setHotelGrade(String hotelGrade) {
        this.hotelGrade = hotelGrade;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }

    public String getHotelGradestring() {
        return hotelGradestring;
    }

    public void setHotelGradestring(String hotelGradestring) {
        this.hotelGradestring = hotelGradestring;
    }
}
