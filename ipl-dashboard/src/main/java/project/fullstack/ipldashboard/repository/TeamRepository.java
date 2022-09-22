package project.fullstack.ipldashboard.repository;

import org.springframework.data.repository.CrudRepository;

import project.fullstack.ipldashboard.Model.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {

    // declaring a method name so that jpa would look at the method name to find
    // what the implementsion should be
    Team findByTeamName(String teamName);
}
