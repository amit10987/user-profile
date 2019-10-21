package userprofile.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "clicks", type = "hotel")
public class Hotel {

    @Id
    private String id;
    private int timestamp;
    private int userId;
    private int hotelId;
    private String hotelRegion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelRegion() {
        return hotelRegion;
    }

    public void setHotelRegion(String hotelRegion) {
        this.hotelRegion = hotelRegion;
    }
}
