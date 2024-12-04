package usecases.chatgpt;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Utility class to parse responses from ChatGPT API.
 */
public class ChatGptResponseParser {

    private static final int LINE_LENGTH = 50; // Adjust this value based on screen width

    /**
     * Parses the response from ChatGPT and extracts the message content.
     * @param response The raw JSON response from ChatGPT.
     * @return The formatted assistant's response content.
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

        // Format the content to ensure it fits on the screen
        return formatResponse(contentNode.asText());
    }

    /**
     * Formats a long response into shorter lines with proper line breaks.
     * @param responseContent The original response content.
     * @return The formatted response with added line breaks.
     */
    private static String formatResponse(String responseContent) {
        StringBuilder formattedResponse = new StringBuilder();
        String[] words = responseContent.split(" ");
        int currentLineLength = 0;

        for (String word : words) {
            if (currentLineLength + word.length() + 1 > LINE_LENGTH) {
                formattedResponse.append("\n"); // Add a new line
                currentLineLength = 0;
            }
            formattedResponse.append(word).append(" ");
            currentLineLength += word.length() + 1;
        }

        return formattedResponse.toString().trim();
    }
}