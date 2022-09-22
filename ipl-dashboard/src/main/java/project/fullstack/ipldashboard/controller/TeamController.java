package project.fullstack.ipldashboard.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.fullstack.ipldashboard.Model.Match;
import project.fullstack.ipldashboard.Model.Team;
import project.fullstack.ipldashboard.repository.MatchRepository;
import project.fullstack.ipldashboard.repository.TeamRepository;

@RestController
@CrossOrigin
public class TeamController {

    private TeamRepository teamRepository;
    private MatchRepository matchRepository;

    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable String teamName) {

        Team team = teamRepository.findByTeamName(teamName);
        // Pageable pageable = PageRequest.of(0, 4);
        team.setMatches(matchRepository.findLatestMatchesByTeam(teamName, 4));

        return team;
    }

    // @GetMapping("/team/{teamName}/matches")
    // public List<Match> getMatchesForTeam(@PathVariable String teamName,
    // @RequestParam int year) {
    // LocalDate startDate = LocalDate.of(year, 1, 1);
    // LocalDate endDate = LocalDate.of(year + 1, 1, 1);
    // return
    // this.matchRepository.getByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(
    // teamName,
    // startDate,
    // endDate,
    // teamName,
    // startDate,
    // endDate);
    // }
    @GetMapping("/team/{teamName}/matches")
    public List<Match> getMatchesForTeam(@PathVariable String teamName, @RequestParam int year) {
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year + 1, 1, 1);
        return this.matchRepository.getMatchesByTeamBetweenDates(
                teamName,
                startDate,
                endDate);
    }

}
