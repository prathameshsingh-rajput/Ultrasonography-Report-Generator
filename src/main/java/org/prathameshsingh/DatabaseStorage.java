package org.prathameshsingh;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

public class DatabaseStorage{

    public DatabaseStorage(String fileName, String patientName, String age, String sex, String doctorName, String reportDate, String lmp, String ga, String aga, String edd1, String edd2, String liverFetusNote, String liverFetusNumNote, String foetalAndSacField, String os, String fetalCardiac, String fbgSmm, String fbgs, String crLmm, String crl, String amnioticFluid, String impressAndComm, String addiNote){
        String encodedUsername = "admin";
        String encodedPassword = "Mongo%401234";
        String connectionString = "mongodb+srv://"+ encodedUsername +":"+ encodedPassword +"@navoday-hospital.g6dcr.mongodb.net/?retryWrites=true&w=majority&appName=Navoday-Hospital";

        // MongoDB client settings
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();

        // Create a new client and connect to the server
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            try {
                // Get the database and collection
                MongoDatabase database = mongoClient.getDatabase("UltrasonographyReports");  // Replace with your database name
                MongoCollection<Document> collection = database.getCollection("MinorReports");

                // Create a new document for the report data
                Document reportDocument = new Document("_id", new ObjectId())
                        .append("id", fileName)
                        .append("patientName", patientName)
                        .append("age", age)
                        .append("sex", sex)
                        .append("doctorName", doctorName)
                        .append("reportDate", reportDate)
                        .append("LMP", lmp)
                        .append("GA", ga)
                        .append("AGA", aga)
                        .append("EDD1", edd1)
                        .append("EDD2", edd2)
                        .append("liverFetusNote", liverFetusNote)
                        .append("liverFetusNumNote", liverFetusNumNote)
                        .append("foetalAndSacField", foetalAndSacField)
                        .append("OS", os)
                        .append("fetalCardiac", fetalCardiac)
                        .append("fbgSmm", fbgSmm)
                        .append("fbgs", fbgs)
                        .append("crLmm", crLmm)
                        .append("crl", crl)
                        .append("amnioticFluid", amnioticFluid)
                        .append("impressAndComm", impressAndComm)
                        .append("addiNote", addiNote);

                // Insert the document into the collection
                collection.insertOne(reportDocument);
            } catch (MongoException e) {
                e.printStackTrace();
            }
        }
    }
//    public static void main(String[]args) {
//        DatabaseStorage obj = new DatabaseStorage();
//        obj.storeMinorRepoMongoDB();
//    }
}
