package entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ratings {

    private final String cleanliness, sleepQuality, rooms, location;
    private final int service, overall, value;

    public Ratings(@JsonProperty("Cleanliness") String cleanliness, @JsonProperty("Sleep Quality") String sleepQuality,
                   @JsonProperty("Rooms") String rooms, @JsonProperty("Location") String location,
                   @JsonProperty("Service") int service, @JsonProperty("Overall") int overall,
                   @JsonProperty("Value") int value) {
        this.cleanliness = cleanliness;
        this.sleepQuality = sleepQuality;
        this.rooms = rooms;
        this.location = location;
        this.service = service;
        this.overall = overall;
        this.value = value;
    }

    public String getCleanliness() {
        return cleanliness;
    }

    public String getSleepQuality() {
        return sleepQuality;
    }

    public String getRooms() {
        return rooms;
    }

    public String getLocation() {
        return location;
    }

    public int getService() {
        return service;
    }

    public int getOverall() {
        return overall;
    }

    public int getValue() {
        return value;
    }
}
