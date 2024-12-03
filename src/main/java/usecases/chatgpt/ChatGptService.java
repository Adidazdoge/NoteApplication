package usecases.chatgpt;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Service to integrate ChatGPT API.
 */
public class ChatGptService {
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    /**
     * Sends a request to ChatGPT with the event description, player attributes, and player choice.
     *
     * @param eventDescription Description of the event.
     * @param playerAttributes Player's attributes as key-value pairs.
     * @param playerChoice     The player's choice for the event.
     * @return The response from ChatGPT.
     * @throws Exception If an error occurs during the API call.
     */
    public String getResponse(String eventDescription, Map<String, Integer> playerAttributes, String playerChoice) throws Exception {
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        // Build the prompt for ChatGPT
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(Map.of(
                "model", "gpt-4",
                "messages", new Object[]{
                        Map.of("role", "system", "content", "You are an AI that helps resolve events in a survival game."),
                        Map.of("role", "user", "content", generatePrompt(eventDescription, playerAttributes, playerChoice))
                },
                "max_tokens", 150,
                "temperature", 0.7
        ));

        System.out.println("Request Body: " + requestBody);

        // Send the request
        try (OutputStream os = connection.getOutputStream()) {
            os.write(requestBody.getBytes());
            os.flush();
        }

        // Read the response
        if (connection.getResponseCode() == 200) {
            return new String(connection.getInputStream().readAllBytes());
        } else {
            // Log the error response
            String errorResponse = new String(connection.getErrorStream().readAllBytes());
            System.err.println("ChatGPT API Error: HTTP " + connection.getResponseCode() + " - " + errorResponse);
            throw new RuntimeException("Failed to call ChatGPT API: HTTP " + connection.getResponseCode());
        }
    }

    /**
     * Generates the prompt for ChatGPT.
     *
     * @param eventDescription Description of the event.
     * @param playerAttributes Player's attributes.
     * @param playerChoice     The player's chosen option.
     * @return The formatted prompt.
     */
    private String generatePrompt(String eventDescription, Map<String, Integer> playerAttributes, String playerChoice) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("Event Description: ").append(eventDescription).append("\n");
        prompt.append("Player Attributes: ").append(playerAttributes).append("\n");
        prompt.append("Player Choice: ").append(playerChoice).append("\n");
        prompt.append("Provide a short paragraph describing the event outcome based on the player's choice.");
        return prompt.toString();
    }
}