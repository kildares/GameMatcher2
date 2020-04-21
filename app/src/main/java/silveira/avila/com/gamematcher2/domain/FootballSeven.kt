package silveira.avila.com.gamematcher2.domain

import silveira.avila.com.gamematcher2.db.entities.Player
import kotlin.random.Random

object FootballSeven : Sports {

    override fun getIdentifier() = "FB7"

    override fun getName() = "Football Seven"

    override fun getPositions(): List<String> {
        return Position.values().map { it.name }
    }

    override fun pickTeams(players: List<Player>, type: PickTeamOptions): List<Team> {
        return when (type) {
            PickTeamOptions.ALL_RANDOM -> pickTeamsRandomly(players)
            PickTeamOptions.BY_POSITION -> pickTeamsByPosition(players)
            PickTeamOptions.ONLY_GOALKEEPER -> pickTeamsWithFixedGoalkeeperOnly(players)
        }

    }

    private fun pickTeamsWithFixedGoalkeeperOnly(players: List<Player>): List<Team> {

        val keepers =
            players.filter { convertPositionsToMutableSet(it.positions).contains(Position.Goal.name) }
                .toMutableList()

        if (keepers.isEmpty()) {
            return pickTeamsRandomly(players)
        }

        val other =
            players.filterNot { convertPositionsToMutableSet(it.positions).contains(Position.Goal.name) }
                .toMutableList()

        val teamList = mutableListOf<Team>()
        val currentTeam = mutableListOf<Player>()
        while (keepers.isNotEmpty() && other.isNotEmpty()) {

            val pos = Random.nextInt(0, other.size)

            val player = other[pos]
            currentTeam.add(player)

            if (currentTeam.size >= 6) {
                teamList.add(Team(currentTeam + listOf(keepers[0])))
                keepers.remove(keepers[0])
                currentTeam.clear()
            }
            other.remove(other[pos])
        }

        return teamList + pickTeamsRandomly(keepers + other + currentTeam)
    }

    private fun pickTeamsRandomly(players: List<Player>): List<Team> {

        val mutablePlayers = players.toMutableList()
        val teamList = mutableListOf<Team>()

        var currentTeam = mutableListOf<Player>()
        while (mutablePlayers.isNotEmpty()) {

            val pos = Random.nextInt(0, mutablePlayers.size)

            val player = mutablePlayers[pos]
            currentTeam.add(player)

            if (currentTeam.size >= 7) {
                teamList.add(Team(currentTeam))
                currentTeam = mutableListOf()
            }
            mutablePlayers.remove(player)
        }

        if (currentTeam.isNotEmpty()) {
            teamList.add(Team(currentTeam))
        }

        return teamList
    }

    private fun pickTeamsByPosition(players: List<Player>): List<Team> {

        val mutablePlayers = players.toMutableList()
        val teams = mutableListOf<Team>()
        while (hasPositionTeamAvailable(mutablePlayers)) {

            var team = getFormationTwoThreeOne(mutablePlayers)
            if(team == null){
                team = getFormationThreeTwoOne(mutablePlayers) ?: error("Error fetching team")
            }
            
            teams.add(team)
            mutablePlayers.removeAll(team.players)
        }
        return teams + pickTeamsWithFixedGoalkeeperOnly(mutablePlayers)
    }

    private fun hasPositionTeamAvailable(players : List<Player>): Boolean {

        if (getFormationTwoThreeOne(players) == null && getFormationThreeTwoOne(players) == null) {
            return false
        }
        return true
    }
    private fun getFormationThreeTwoOne(players: List<Player>): Team? {
        val keepers =
            players.filter { convertPositionsToMutableSet(it.positions).contains(Position.Goal.name) }
                .toMutableList()
        val defenseSide =
            players.filter { convertPositionsToMutableSet(it.positions).contains(Position.Defense.name) ||
                    convertPositionsToMutableSet(it.positions).contains(Position.Side.name)
            }
                .toMutableList()
        val midfield =
            players.filter { convertPositionsToMutableSet(it.positions).contains(Position.Midfield.name) }
                .toMutableList()
        val attack =
            players.filter { convertPositionsToMutableSet(it.positions).contains(Position.Attack.name) }
                .toMutableList()

        if(keepers.size < 1 || (defenseSide.size < 3)  || midfield.size < 2 || attack.size < 1){
            return null
        }

        val defenseOne = defenseSide.random()

        defenseSide.remove(defenseOne)

        val defenseTwo = defenseSide.random()

        defenseSide.remove(defenseTwo)

        val defenseThree = defenseSide.random()

        val midfieldOne = midfield.random()

        midfield.remove(midfieldOne)

        val midfieldTwo = midfield.random()

        val attackOne = attack.random()

        val keeperOne = keepers.random()

        return Team(listOf(keeperOne, defenseOne, defenseTwo, defenseThree, midfieldOne, midfieldTwo,  attackOne))
    }

    private fun getFormationTwoThreeOne(players : List<Player>): Team? {

        val keepers =
            players.filter { convertPositionsToMutableSet(it.positions).contains(Position.Goal.name) }
                .toMutableList()
        val defense =
            players.filter { convertPositionsToMutableSet(it.positions).contains(Position.Defense.name) }
                .toMutableList()
        val midfield =
            players.filter { convertPositionsToMutableSet(it.positions).contains(Position.Midfield.name) }
                .toMutableList()
        val attack =
            players.filter { convertPositionsToMutableSet(it.positions).contains(Position.Attack.name) }
                .toMutableList()
        val side =
            players.filter { convertPositionsToMutableSet(it.positions).contains(Position.Side.name) }
                .toMutableList()

        if(keepers.size < 1 || defense.size < 2 || midfield.size < 1 || side.size < 2 || attack.size < 1){
            return null
        }

        val defenseOne = defense.random()
        defense.remove(defenseOne)
        var defenseTwo = defense.random()

        val midfieldOne = midfield.random()

        val sideOne = side.random()
        side.remove(sideOne)
        val sideTwo = side.random()

        val attackOne = attack.random()

        val keeperOne = keepers.random()

        return Team(listOf(keeperOne, defenseOne, defenseTwo, midfieldOne, sideOne, sideTwo, attackOne))
    }

    override fun convertPositionsToStr(selectedPositions: MutableSet<String>): String {
        var position = if (selectedPositions.contains(Position.Goal.name)) "1" else "0"
        position += if (selectedPositions.contains(Position.Defense.name)) "1" else "0"
        position += if (selectedPositions.contains(Position.Midfield.name)) "1" else "0"
        position += if (selectedPositions.contains(Position.Side.name)) "1" else "0"
        position += if (selectedPositions.contains(Position.Attack.name)) "1" else "0"
        return position
    }

    override fun convertPositionsToMutableSet(positions: String): MutableSet<String> {

        val set = mutableSetOf<String>()
        if (positions.substring(0, 1) == "1") set.add(Position.Goal.name)
        if (positions.substring(1, 2) == "1") set.add(Position.Defense.name)
        if (positions.substring(2, 3) == "1") set.add(Position.Midfield.name)
        if (positions.substring(3, 4) == "1") set.add(Position.Side.name)
        if (positions.substring(4, 5) == "1") set.add(Position.Attack.name)
        return set
    }

    override fun isFormalAndShownIdentical(formalPosition: String, shownPosition: String): Boolean {
        return (formalPosition.substring(0, 1) == "1" && shownPosition == Position.Goal.name)
                || (formalPosition.substring(1, 2) == "1" && shownPosition == Position.Defense.name)
                || (formalPosition.substring(
            2,
            3
        ) == "1" && shownPosition == Position.Midfield.name)
                || (formalPosition.substring(3, 4) == "1" && shownPosition == Position.Side.name)
                || (formalPosition.substring(4, 5) == "1" && shownPosition == Position.Attack.name)
    }

    private enum class Position(val code: Byte) {
        Goal(1),
        Defense(2),
        Midfield(4),
        Side(8),
        Attack(16)
    }
}