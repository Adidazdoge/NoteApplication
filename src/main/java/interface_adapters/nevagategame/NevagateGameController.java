package interface_adapters.nevagategame;

import usecases.nevagategame.NevagateGameInputBoundary;
import usecases.nevagategame.NevagateGameInputdata;

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