package entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;

public class Review {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss z yyyy");

    private final String author, authorLocation, content, reviewID, title;
    private final LocalDateTime date;
    private final Ratings ratings;

    public Review(@JsonProperty("Author") String author, @JsonProperty("AuthorLocation") String authorLocation,
                  @JsonProperty("Content") String content, @JsonProperty("ReviewID") String reviewID,
                  @JsonProperty("Title") String title, @JsonProperty("Date") String date,
                  @JsonProperty("Ratings") Ratings ratings) {
        this.author = author;
        this.authorLocation = authorLocation;
        this.content = content;
        this.reviewID = reviewID;
        this.title = title;
        this.date = LocalDateTime.parse(date, FORMATTER);
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

    public LocalDateTime getDate() {
        return date;
    }

    public Ratings getRatings() {
        return ratings;
    }
}
