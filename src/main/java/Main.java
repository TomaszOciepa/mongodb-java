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


        Document document1 = new Document();
        document1.put("Mark", "Fiat");
        document1.put("Model", "126p");

        Document document2 = new Document();
        document2.put("Mark", "BMW");
        document2.put("Model", "X5");
        document2.put("Color", "Black");

        mongoCollection.insertMany(Arrays.asList(document1, document2));
    }
}
