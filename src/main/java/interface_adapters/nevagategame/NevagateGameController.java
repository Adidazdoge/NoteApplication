package interface_adapters.nevagategame;

import usecases.nevagateGame.NevagateGameInputBoundary;
import usecases.nevagateGame.NevagateGameInputdata;

public class NevagateGameController {
    private final NevagateGameInputBoundary interactor;

    public NevagateGameController(NevagateGameInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute() {
        final NevagateGameInputdata inputdata = new NevagateGameInputdata();
        interactor.execute(inputdata);
    }
}