package usecases.chatgpt;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Utility class to parse responses from ChatGPT API.
 */
public class ChatGptResponseParser {

    /**
     * Parses the response from ChatGPT and extracts the event outcome description.
     *
     * @param response The raw JSON response from ChatGPT.
     * @return The event outcome description.
     * @throws Exception If the parsing fails.
     */
    public static String parseEventOutcome(String response) throws Exception {
        // Log the raw response
        System.out.println("Raw response from ChatGPT: " + response);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(response);

        // Navigate the response structure
        JsonNode choicesNode = rootNode.path("choices");
        if (!choicesNode.isArray() || choicesNode.isEmpty()) {
            throw new RuntimeException("Invalid ChatGPT response format: 'choices' array is missing or empty.");
        }

        JsonNode firstChoice = choicesNode.get(0);
        JsonNode messageNode = firstChoice.path("message");
        if (messageNode.isMissingNode()) {
            throw new RuntimeException("Invalid ChatGPT response format: 'message' node is missing.");
        }

        JsonNode contentNode = messageNode.path("content");
        if (contentNode.isMissingNode() || contentNode.asText().isEmpty()) {
            throw new RuntimeException("Invalid ChatGPT response format: 'content' is missing or empty.");
        }

        return contentNode.asText().trim();
    }
}