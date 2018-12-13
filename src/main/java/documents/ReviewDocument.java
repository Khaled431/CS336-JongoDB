package documents;

import com.fasterxml.jackson.annotation.JsonProperty;
import entity.HotelInfo;
import entity.Review;
import org.bson.types.ObjectId;

public class ReviewDocument {

    private final ObjectId id;
    private final HotelInfo info;
    private final Review[] reviews;

    public ReviewDocument(@JsonProperty("_id") ObjectId _id, @JsonProperty("HotelInfo") HotelInfo info,
                          @JsonProperty("Reviews") Review[] reviews) {
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

    public Review[] getReviews() {
        return reviews;
    }
}
