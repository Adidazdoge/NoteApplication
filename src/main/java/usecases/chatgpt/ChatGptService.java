package usecases.chatgpt;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Service to integrate ChatGPT API.
 */
public class ChatGptService {
    private static final String API_URL = "https://api.openai.com/v1/completions";
    private static final String API_KEY = "";

    /**
     * Sends a request to ChatGPT with the event description, player attributes, and choices.
     * @param eventDescription Description of the event.
     * @param playerAttributes Player's attributes as key-value pairs.
     * @param choices Event choices.
     * @return The response from ChatGPT.
     * @throws Exception If an error occurs during the API call.
     */
    public String getResponse(String eventDescription, Map<String, Integer> playerAttributes, Map<Integer, String> choices) throws Exception {
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        // Build the prompt for ChatGPT
        String prompt = generatePrompt(eventDescription, playerAttributes, choices);

        // Construct the request payload
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(Map.of(
                "model", "gpt-4-turbo", // Updated to use GPT-4 Turbo
                "messages", new Object[] { Map.of("role", "user", "content", prompt) },
                "max_tokens", 150,
                "temperature", 0.7
        ));

        // Send the request
        try (OutputStream os = connection.getOutputStream()) {
            os.write(requestBody.getBytes());
            os.flush();
        }

        // Read and process the response
        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                return response.toString();
            }
        } else {
            try (BufferedReader errorReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()))) {
                StringBuilder errorResponse = new StringBuilder();
                String line;
                while ((line = errorReader.readLine()) != null) {
                    errorResponse.append(line);
                }
                throw new RuntimeException("ChatGPT API Error: " + responseCode + " - " + errorResponse);
            }
        }
    }

    /**
     * Generates the prompt for ChatGPT.
     * @param eventDescription Description of the event.
     * @param playerAttributes Player's attributes.
     * @param choices Event choices.
     * @return The formatted prompt.
     */
    private String generatePrompt(String eventDescription, Map<String, Integer> playerAttributes, Map<Integer, String> choices) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("Event Description: ").append(eventDescription).append("\n");
        prompt.append("Player Attributes: ").append(playerAttributes).append("\n");
        prompt.append("Choices:\n");
        choices.forEach((key, value) -> prompt.append(key).append(". ").append(value).append("\n"));
        prompt.append("Choose the best option and provide a short paragraph explaining the result.");
        return prompt.toString();
    }
}