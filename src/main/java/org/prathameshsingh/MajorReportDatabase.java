package org.prathameshsingh;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

public class MajorReportDatabase{
    public MajorReportDatabase(String fileName, String patientNameM, String ageM, String sexPatientM, String currDateM, String drNameM, String livesData, String liveDataBold, String foetalCardiacData, String placentaGradeData, String fsr, String placentalClotsData, String afi, String osData, String amnioticFluidData, String umbilicalCordData, String lmp, String edd1, String edd2, String rc00, String rc01, String rc02, String rc10, String rc11, String rc12, String rc20, String rc21, String rc22, String rc30, String rc31, String rc32, String avgAge, String foetalWeight, String imp, String addiNote){
        String connectionString = "mongodb+srv://:connect-psr@navoday-hospital.g6dcr.mongodb.net/?retryWrites=true&w=majority&appName=Navoday-Hospital";
        System.out.println("Inside Major Databse..");
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
                MongoDatabase database = mongoClient.getDatabase("UltrasonographyReports");
                MongoCollection<Document> collection = database.getCollection("MajorReports");

                // Create a new document for the report data
                Document reportDocument = new Document("_id", new ObjectId())
                        .append("id", fileName)
                        .append("patientName", patientNameM)
                        .append("age", ageM)
                        .append("sex", sexPatientM)
                        .append("doctorName", drNameM)
                        .append("reportDate", currDateM)
                        .append("LMP", lmp)
                        .append("EDD1", edd1)
                        .append("EDD2", edd2)
                        .append("LivesData", livesData)
                        .append("LiveDataBold", liveDataBold)
                        .append("FoetalCardiacData", foetalCardiacData)
                        .append("PlacentaGradeData", placentaGradeData)
                        .append("FSR", fsr)
                        .append("PlacentalClotsData", placentalClotsData)
                        .append("AFI", afi)
                        .append("OSData", osData)
                        .append("AmnioticFluidData", amnioticFluidData)
                        .append("UmbilicalCordData", umbilicalCordData)
                        .append("RC00", rc00)
                        .append("RC01", rc01)
                        .append("RC02", rc02)
                        .append("RC10", rc10)
                        .append("RC11", rc11)
                        .append("RC12", rc12)
                        .append("RC20", rc20)
                        .append("RC21", rc21)
                        .append("RC22", rc22)
                        .append("RC30", rc30)
                        .append("RC31", rc31)
                        .append("RC32", rc32)
                        .append("AverageAge", avgAge)
                        .append("FoetalWeight", foetalWeight)
                        .append("Impression", imp)
                        .append("AdditionalNote", addiNote);

                // Insert the document into the collection
                collection.insertOne(reportDocument);
            } catch (MongoException e) {
                e.printStackTrace();
            }
        }
    }

}
