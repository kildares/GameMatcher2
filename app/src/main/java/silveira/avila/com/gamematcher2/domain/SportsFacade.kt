package silveira.avila.com.gamematcher2.domain

import silveira.avila.com.gamematcher2.db.entities.Player

object  SportsFacade{

    private val sportsMap : Map<String, Sports> = mapOf(Pair(FootballSeven.getIdentifier(), FootballSeven))

    fun getAvailableSports() : List<String>{
        return sportsMap.values.map { it.getName() }
    }

    fun getPositions(identifier : String) : List<String>{
        return sportsMap.getOrElse(identifier){throw Exception("Unsupported sport")}.getPositions()
    }

    fun getSport(identifier : String) : Sports {
        return sportsMap.getOrElse(identifier) { throw Exception("Unsupported sport") }
    }

    fun getSportIdentifierByName(name : String) : String {
        return sportsMap.values.first {it.getName() == name}.getIdentifier()
    }

    fun convertPositionsToStr(identifier: String, selectedPositions: MutableSet<String>): String {
        return sportsMap.getOrElse(identifier){throw Exception("Unsupported sport")}.convertPositionsToStr(selectedPositions)
    }

    fun convertPositionsToMutableSet(identifier: String, positions : String) : MutableSet<String>{
        return sportsMap.getOrElse(identifier){ throw Exception("Unsupported sport") }.convertPositionsToMutableSet(positions)
    }

    fun isFormalAndShownIdentical(identifier : String, formalPosition : String, shownPosition : String ) : Boolean {
        return sportsMap.getOrElse(identifier){throw Exception("Unsupported sport")}.isFormalAndShownIdentical(formalPosition, shownPosition)
    }

    fun pickTeams(identifier : String, players : List<Player>, option : PickTeamOptions ) : List<Team> {
        return sportsMap.getOrElse(identifier){throw Exception("Unsupported sport")}.pickTeams(players, option)
    }

}