package usecases.eventrespond.blizzard;

/**
 * Input boundary for handling Blizzard event responses.
 */
public interface BlizzardInputBoundary {
    void execute(BlizzardInputData inputData);
}
