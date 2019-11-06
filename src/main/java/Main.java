import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("vehicles"); //db
        MongoCollection mongoCollection = mongoDatabase.getCollection("cars"); //collection - tabela

//        save(mongoCollection);
        read(mongoCollection);
        readByParam(mongoCollection, "Mark", "SUBARU");
    }

    private static void readByParam(MongoCollection mongoCollection, String param, Object value) {
        Document document = new Document();
        document.put(param, value);
        Document first = (Document) mongoCollection.find(document).first();
        System.out.println(first.toJson());
    }

    private static void read(MongoCollection mongoCollection) {

        Document first = (Document) mongoCollection.find().first();
        System.out.println(first.toJson());
    }

    private static void save(MongoCollection mongoCollection) {
        Document document1 = new Document();
        document1.put("Mark", "Fiat");
        document1.put("Model", "126p");

        Document document2 = new Document();
        document2.put("Mark", "BMW");
        document2.put("Model", "X5");
        document2.put("Color", "Black");

        Document document3 = new Document();
        document3.put("Mark", "SUBARU");
        document3.put("Model", "WRX");
        document3.put("Color", Arrays.asList("blue", "green", "black"));

        mongoCollection.insertMany(Arrays.asList(document1, document2, document3));
    }
}
