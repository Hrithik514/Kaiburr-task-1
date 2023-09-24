import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.bson.Document;

public class server extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Define the MongoDB connection string (URI)
        String connectionString = "mongodb://localhost:27017"; // Change as needed
        
        // Create a MongoClient using the connection string
        MongoClientURI uri = new MongoClientURI(connectionString);
        MongoClient mongoClient = new MongoClient(uri);
        
        // Access a specific database
        MongoDatabase database = mongoClient.getDatabase("mydb"); // Change to your database name
        
        // Access a specific collection within the database
        MongoCollection<Document> collection = database.getCollection("mycollection"); // Change to your collection name
        
        // Parse form data
        String name = request.getParameter("name");
        int id = Integer.parseInt(request.getParameter("id"));
        String language = request.getParameter("language");
        String framework = request.getParameter("framework");
        
        // Insert a document into the collection
        Document document = new Document("name", name)
                .append("id", id)
                .append("language", language)
                .append("framework", framework);
        
        collection.insertOne(document);
        
        // Close the MongoClient when done
        mongoClient.close();
        
        // Redirect to a success page or display a success message
        response.sendRedirect("success.html");
    }
}
