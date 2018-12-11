package documents;

import com.fasterxml.jackson.annotation.JsonProperty;
import entity.HotelInfo;
import entity.Reviews;
import org.bson.types.ObjectId;

public class ReviewDocument {

    private final ObjectId id;
    private final HotelInfo info;
    private final Reviews[] reviews;

    public ReviewDocument(@JsonProperty("_id") ObjectId _id, @JsonProperty("HotelInfo") HotelInfo info,
                          @JsonProperty("Reviews") Reviews[] reviews) {
        this.id = _id;
        this.info = info;
        this.reviews = reviews;
    }

    public ObjectId getId() {
        return id;
    }

    public HotelInfo getInfo() {
        return info;
    }

    public Reviews[] getReviews() {
        return reviews;
    }
}
