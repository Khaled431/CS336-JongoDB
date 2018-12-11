package main;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import documents.ReviewDocument;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;

public class MongoReader {

    public static void main(String[] args) {
        try {
            DB db = new MongoClient().getDB("cs336");

            Jongo jongo = new Jongo(db);
            MongoCollection collection = jongo.getCollection("smaller_reviews");

            MongoCursor<ReviewDocument> all = collection.find().as(ReviewDocument.class);
            all.forEach(document -> {
                System.out.println(document.getId());
            });

            System.out.println(all.count());
        } catch (MongoException e) {
            e.printStackTrace();
        }
    }
}
