package entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HotelInfo {

    private final String address, hotelID, hotelURL, imgURL, name, price;

    public HotelInfo(@JsonProperty("Address") String address,
                     @JsonProperty("HotelID") String hotelID, @JsonProperty("HotelURL") String hotelURL,
                     @JsonProperty("ImgURL") String imgURL, @JsonProperty("Name") String name,
                     @JsonProperty("Price") String price) {
        this.address = address;
        this.hotelID = hotelID;
        this.hotelURL = hotelURL;
        this.imgURL = imgURL;
        this.name = name;
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public String getHotelID() {
        return hotelID;
    }

    public String getHotelURL() {
        return hotelURL;
    }

    public String getImgURL() {
        return imgURL;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}
