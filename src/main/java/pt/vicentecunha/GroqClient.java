package pt.vicentecunha;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GroqClient {
    private final String API_KEY;
    private static final String API_URL = "https://api.groq.com/openai/v1/chat/completions";
    private final HttpClient client;
    private final ObjectMapper mapper = new ObjectMapper();

    private final static String ERROR = "ERROR";

    public GroqClient(String API_KEY) {
        this.API_KEY = API_KEY;
        this.client = HttpClient.newHttpClient();
    }

    public String singleQuery(String systemOrder, String topic) {

        ObjectNode requestBody = mapper.createObjectNode();
        ArrayNode messages = requestBody.putArray("messages");

        ObjectNode message = messages.addObject();

        message.put("role", "system");
        message.put("content", systemOrder); //system commands
        ObjectNode message2 = messages.addObject();
        message2.put("role", "user");
        message2.put("content", topic); //user query

        requestBody.put("model", "mixtral-8x7b-32768");




        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return parseMessageContent(response.body());
        } catch (IOException | InterruptedException e) {
            return ERROR;
        }
    }

    public String singleQuery(String topic) {

        ObjectNode requestBody = mapper.createObjectNode();
        ArrayNode messages = requestBody.putArray("messages");

        ObjectNode message = messages.addObject();

        message.put("role", "user");
        message.put("content", topic); //user query

        requestBody.put("model", "mixtral-8x7b-32768");



        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return parseMessageContent(response.body());
        } catch (IOException | InterruptedException e) {
            return ERROR;
        }
    }


    private static String parseMessageContent(String jsonResponse) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode rootNode = mapper.readTree(jsonResponse);

            return rootNode.path("choices").get(0).path("message").path("content").asText();
        } catch (Exception e) {
            return ERROR;
        }


    }

}
