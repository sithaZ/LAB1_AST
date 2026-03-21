package Lab01;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class Authentication {
    private final String API_URL = "http://localhost:3000/api/login";

    public CompletableFuture<Boolean> login(String username, String password) {
       
        String json = String.format("{\"username\":\"%s\", \"password\":\"%s\"}", username, password);
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> response.statusCode() == 201); 
                // NestJS returns 201 Created for successful POST by default
    }
}