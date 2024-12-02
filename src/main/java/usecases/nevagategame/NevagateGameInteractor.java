package usecases.nevagategame;

public class NevagateGameInteractor implements NevagateGameInputBoundary{
    private NevagateGameDataAccessInterface dataaccess;
    private NevagateGameOutputBoundary outputboundary;

    public NevagateGameInteractor(NevagateGameDataAccessInterface dataaccess,
                                  NevagateGameOutputBoundary outputboundary) {
        this.dataaccess = dataaccess;
        this.outputboundary = outputboundary;
    }

    @Override
    public void execute(NevagateGameInputdata InputData) {
        final NevagateGameOutputdata outputdata = new NevagateGameOutputdata();
        outputboundary.prepareGamePage(outputdata);
    }
}
