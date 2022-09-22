/* 
**  
Intermediate Processor
======================
**
*/

package project.fullstack.ipldashboard.data;

import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import project.fullstack.ipldashboard.Model.Match;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

    private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

    @Override
    public Match process(final MatchInput matchInput) throws Exception {

        Match match = new Match();
        match.setId(Long.parseLong(matchInput.getId()));
        match.setCity(matchInput.getCity());
        match.setDate(LocalDate.parse(matchInput.getDate()));
        match.setPlayerOfMatch(matchInput.getPlayerOfMatch());
        match.setVenue(matchInput.getVenue());

        // setting team1 and team2 as per the ininings order
        String firstIningsTeam, secondIniningsTeam;

        // firstIningsTeam = matchInput.getToss_decision() == "bat" ?
        // matchInput.getToss_winner() : matchInput.getTeam2();

        if ("bat".equals(matchInput.getToss_decision())) {
            firstIningsTeam = matchInput.getToss_winner();
            secondIniningsTeam = matchInput.getToss_winner().equals(matchInput.getTeam1())
                    ? matchInput.getTeam2()
                    : matchInput.getTeam1();
        } else {
            secondIniningsTeam = matchInput.getToss_winner();
            firstIningsTeam = matchInput.getToss_winner().equals(matchInput.getTeam1())
                    ? matchInput.getTeam2()
                    : matchInput.getTeam1();
        }

        match.setTeam1(firstIningsTeam);
        match.setTeam2(secondIniningsTeam);

        match.setTossWinner(matchInput.getToss_winner());
        match.setTossDecision(matchInput.getToss_decision());
        match.setResult(matchInput.getResult());
        match.setMatchWinner(matchInput.getWinner());
        match.setResultMargin(matchInput.getResult_margin());
        match.setUmpire1(matchInput.getUmpire1());
        match.setUmpire2(matchInput.getUmpire2());

        return match;

    }

}
