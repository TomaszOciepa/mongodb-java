import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Arrays;

import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

public class Main {

    public static void main(String[] args) {

        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("vehicles"); //db
        MongoCollection mongoCollection = mongoDatabase.getCollection("cars"); //collection - tabela

//        save(mongoCollection);
//        read(mongoCollection);
//        readByParam(mongoCollection, "Mark", "SUBARU");
//        readByParamAllElement(mongoCollection, "Mark", "BMW");
//        delete(mongoCollection, "Mark", "BMW");
//        update(mongoCollection, "Mark", "SUBARU", "Model", "Impreza");

        MongoCollection mongoCollectionBike = mongoDatabase.getCollection("bike");
        saveBike(mongoCollectionBike);
    }

    private static void saveBike(MongoCollection mongoCollectionBike) {
        Document document = new Document();
        document.put("name", "Kross");

        Document documentPerson = new Document();
        documentPerson.put("name", "Tomasz");
        documentPerson.put("surname", "Ociepa");
        document.put("owner", documentPerson);

        mongoCollectionBike.insertOne(document);
    }


    private static void delete(MongoCollection mongoCollection, String param, String value) {
        Document document = new Document();
        document.put(param, value);
//        mongoCollection.deleteOne(document);
        mongoCollection.deleteMany(document);
    }

    private static void update(MongoCollection mongoCollection, String param, String value, String newParam, String newValue) {
        Bson eq = Filters.eq(param, value);
        Bson query = combine(set(newParam, newValue), set("Power-engine", "360KM"));
        mongoCollection.updateOne(eq, query);
    }


    private static void readByParamAllElement(MongoCollection mongoCollection, String param, String value) {
        Document document = new Document();
        document.put(param, value);
        MongoCursor iterator = mongoCollection.find(document).iterator();

        while (iterator.hasNext()) {
            Document next = (Document) iterator.next();
            System.out.println(next.toJson());
        }

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
