package use_case.move;


/**
 * Inputdata structure for move usecase.
 */
public class MoveInputData {
    private final String direction;

    public MoveInputData(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }
}
