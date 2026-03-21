package Lab01;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class Authentication {
    // Ensure this matches the @Controller('api') and @Post('login') in NestJS
    private final String API_URL = "http://localhost:3000/api/login";

    public CompletableFuture<Boolean> login(String username, String password) {
        // Formats the JSON body for the NestJS @Body() loginData
        String json = String.format("{\"username\":\"%s\", \"password\":\"%s\"}", username, password);
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> response.statusCode() == 201); 
    }
}