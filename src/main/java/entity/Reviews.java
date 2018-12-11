package entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Reviews {

    private final String author, authorLocation, content, reviewID, title;
    private final java.util.Date date;
    private final Ratings ratings;

    public Reviews(@JsonProperty("Author") String author, @JsonProperty("AuthorLocation") String authorLocation,
                   @JsonProperty("Content") String content, @JsonProperty("ReviewID") String reviewID,
                   @JsonProperty("Title") String title, @JsonProperty("Date") java.util.Date date,
                   @JsonProperty("Ratings") Ratings ratings) {
        this.author = author;
        this.authorLocation = authorLocation;
        this.content = content;
        this.reviewID = reviewID;
        this.title = title;
        this.date = date;
        this.ratings = ratings;
    }

    public String getAuthor() {
        return author;
    }

    public String getAuthorLocation() {
        return authorLocation;
    }

    public String getContent() {
        return content;
    }

    public String getReviewID() {
        return reviewID;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public Ratings getRatings() {
        return ratings;
    }
}
