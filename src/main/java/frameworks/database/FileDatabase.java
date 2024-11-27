package frameworks.database;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * A utility class for managing a file-based JSON database.
 * This class allows reading and writing a list of data objects to a JSON file
 * specified by the user. It uses Jackson for JSON serialization and deserialization.
 *
 * @param <T> The type of the data objects that will be stored in the database.
 */
public class FileDatabase<T> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String filePath;
    private final TypeReference<List<T>> typeReference;

    /**
     * Constructs a new FileDatabase instance.
     *
     * @param filePath      The path to the JSON file that will be used for storage.
     * @param typeReference A TypeReference to handle generic type information for deserialization.
     */
    public FileDatabase(String filePath, TypeReference<List<T>> typeReference) {
        this.filePath = filePath;
        this.typeReference = typeReference;
    }

    /**
     * Loads data from the JSON file.
     *
     * @return A list of data objects read from the file. If the file does not exist,
     *         an empty list is returned.
     * @throws IOException If the file cannot be read or parsed.
     */
    public List<T> load() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            return List.of();
        }
        return objectMapper.readValue(file, typeReference);
    }

    /**
     * Saves data to the JSON file.
     *
     * @param data The list of data objects to save to the file.
     * @throws IOException If the file cannot be written.
     */
    public void save(List<T> data) throws IOException {
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), data);
    }
}
