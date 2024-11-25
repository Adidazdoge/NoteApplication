package interface_adapters.presenters;

import java.util.List;
import java.util.stream.Collectors;

import frameworks.view.RankingViewModel;
import usecases.ranking.RankingResponse;

/**
 * Converts the RankingResponse data into a format suitable for the UI layer.
 */
public class RankingPresenter {

    /**
     * Converts the RankingResponse into a list of RankingViewModel objects.
     *
     * @param response The response from the RankingUseCase.
     * @return A list of RankingViewModel objects for the UI layer.
     */
    public List<RankingViewModel> present(RankingResponse response) {
        return response.getRankings().stream()
                .map(entry -> {
                    return new RankingViewModel(
                            entry.getName(),
                            entry.getScore(),
                            entry.getDaysSurvived(),
                            entry.isWon());
                })
                .collect(Collectors.toList());
    }
}
