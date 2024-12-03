package usecases.chatgpt;

/**
 * Utility class to parse ChatGPT responses.
 */
public class ChatGptResponseParser {

    /**
     * Parses the choice (1, 2, or 3) from the ChatGPT response.
     *
     * @param chatResponse The response from ChatGPT.
     * @return The parsed choice as an integer.
     * @throws IllegalArgumentException If the choice cannot be parsed.
     */
    public int parseChoice(String chatResponse) {
        try {
            return Integer.parseInt(chatResponse.split("\\.")[0].trim());
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid ChatGPT response format: " + chatResponse);
        }
    }

    /**
     * Parses the descriptive paragraph from the ChatGPT response.
     *
     * @param chatResponse The response from ChatGPT.
     * @return The parsed description as a string.
     */
    public String parseDescription(String chatResponse) {
        int colonIndex = chatResponse.indexOf(":");
        if (colonIndex == -1 || colonIndex + 1 >= chatResponse.length()) {
            throw new IllegalArgumentException("Invalid ChatGPT response format: " + chatResponse);
        }
        return chatResponse.substring(colonIndex + 1).trim();
    }
}