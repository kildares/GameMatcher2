package matcher.game.silveira.avila.com.gamematcher2.sports

import matcher.game.silveira.avila.com.gamematcher2.db.entities.Player
import java.util.stream.Collector

object SportsFacade{

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

    fun sortPlayersBySport(identifier : String, players : List<Player>) : List<Team>{
        return sportsMap.getOrElse(identifier) { throw Exception("Unsupported sport") }.sortByPositions(players)
    }

    fun convertPositionsToStr(identifier: String, selectedPositions: MutableSet<String>): String {
        return sportsMap.getOrElse(identifier){throw Exception("Unsupported sport")}.convertPositionsToStr(selectedPositions)
    }

    fun ConvertPositionsToMutableSet(identifier: String, positions : String) : MutableSet<String>{
        return sportsMap.getOrElse(identifier){ throw Exception("Unsupported sport") }.convertPositionsToMutableSet(positions)
    }

}