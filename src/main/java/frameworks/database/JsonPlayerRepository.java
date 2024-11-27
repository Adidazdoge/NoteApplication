package frameworks.database;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import entities.Player;
import interface_adapters.gateways.PlayerRepository;

/**
 * A JSON-based implementation of the PlayerRepository interface.
 */
public class JsonPlayerRepository implements PlayerRepository {
    private final FileDatabase<Player> database;
    private List<Player> players;

    public JsonPlayerRepository(String filePath) throws IOException {
        this.database = new FileDatabase<>(filePath, new TypeReference<List<Player>>() {});
        this.players = database.load();
    }

    @Override
    public Player findByUsername(String username) {
        return players.stream()
                .filter(player -> player.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean isUsernameDuplicate(String username) {
        return findByUsername(username) != null;
    }

    @Override
    public void addPlayer(Player player) {
        players.add(player);
        try {
            database.save(players);
        }
        catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
