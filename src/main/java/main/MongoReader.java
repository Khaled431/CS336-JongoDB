package main;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import documents.ReviewDocument;
import entity.HotelInfo;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MongoReader {

    public static void main(String[] args) {
        try {
            DB db = new MongoClient().getDB("cs336");

            Jongo jongo = new Jongo(db);
            MongoCollection collection = jongo.getCollection("reviews");

            MongoCursor<ReviewDocument> all = collection.find().as(ReviewDocument.class);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File("./out.sql")))) {
                all.forEach(doc -> outStructure(writer, doc));
            }
            System.out.println(all.count());
        } catch (MongoException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void outStructure(BufferedWriter writer, ReviewDocument doc) {
        try {
            writeHotelInfo(writer, doc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeHotelInfo(BufferedWriter writer, ReviewDocument doc) throws IOException {
        HotelInfo info = doc.getInfo();

        String address = info.getAddress();
        String street = "";
        String locality = "";
        String postal = "";
        String region = "";
        String country = address != null ? "United States" : "";

        if (address != null) {
            Document addressDocument = Jsoup.parse(address);
            Elements elements = addressDocument.select("span");
            for (Element e : elements) {
                if (!e.hasAttr("property"))
                    continue;
                String value = e.attr("property");
                if (value.equals("v:street-address"))
                    street = e.ownText();
                else if (value.equals("v:locality"))
                    locality = e.ownText();
                else if (value.equals("v:region"))
                    region = e.ownText();
                else if (value.equals("v:postal-code"))
                    postal = e.ownText();
                else if (value.equals("v:country-name"))
                    country = e.ownText();
            }
        }

        String url = info.getHotelURL();
        if (url != null) {
            url = url.replace("http://www.tripadvisor.com", "");
        }

        String priceText = getFixedPrice(info.getPrice());
        int lowPrice = 0, highPrice = 0;
        if (priceText != null && !priceText.isEmpty()) {
            int seperator = priceText.indexOf("-");
            int plus = priceText.indexOf("+");
            if (seperator != -1) {
                lowPrice = Integer.parseInt(priceText.substring(0, seperator));
                highPrice = Integer.parseInt(priceText.substring(seperator + 1));
            } else if (plus != -1) {
                lowPrice = Integer.parseInt(priceText.substring(0, plus));
                highPrice = Integer.MAX_VALUE;
            } else {
                lowPrice = Integer.parseInt(priceText);
            }
        }

        writer.write("INSERT INTO " + HOTEL_TABLE_SCHEMA + " " + values(info.getHotelID(), info.getName(), street, locality, postal, region, country,
                lowPrice, highPrice, url, info.getImgURL()));
        writer.newLine();
        writer.flush();
    }

    private static final String HOTEL_TABLE_SCHEMA = "hotelinfo(`id`,`name`,`street`,`locality`,`postal`,`region`,`country`,"
                                                     + "`priceLow`,`priceHigh`,`url`,`imgURL`)";

    private static String values(Object... values) {
        StringBuilder builder = new StringBuilder();
        builder.append("VALUES(");

        for (int index = 0, len = values.length; index < len; index++) {
            Object value = values[index];
            builder.append(value == null ? "''" : "'" + value.toString().replace("\'", "\\\'") + "'");
            if (index != len - 1)
                builder.append(",");
        }

        builder.append(");");

        return builder.toString();
    }

    private static String getFixedPrice(String old) {
        if (old.isEmpty() || old.equals("Unkown")) {
            return null;
        }
        old = old.replace(" and up", "+");
        StringBuilder builder = new StringBuilder();
        for (char c : old.toCharArray()) {
            if (!(Character.isDigit(c) || c == '-' || c == '+'))
                continue;
            builder.append(c);
        }

        return builder.toString();
    }
}
