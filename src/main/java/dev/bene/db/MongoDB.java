package dev.bene.db;

import org.bson.Document;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.FindIterable;
import static com.mongodb.client.model.Filters.eq;

public class MongoDB {
    private static MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> collection;

    public MongoDB() {
        connectDB();

        database = mongoClient.getDatabase("unit-converter");
        collection = database.getCollection("unit-converter");
    }

    public void connectDB() {
        try {
            mongoClient = MongoClients.create("mongodb://localhost:27017");
        } catch (MongoException e) {
            System.out.println("Error connecting to database: " + e);
        }
    }

    public void setHistory(Document doc) {
        try {
            collection.insertOne(doc);
        } catch (MongoException e) {
            System.out.println("Error inserting document: " + e);
        }
    }

    public FindIterable<Document> getHistory() {
        try {
            return collection.find(eq("history", true));
        } catch (MongoException e) {
            System.out.println("Error getting documents: " + e);
            return null;
        }
    }
}
