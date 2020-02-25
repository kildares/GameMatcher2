package matcher.game.silveira.avila.com.gamematcher2.sports

import matcher.game.silveira.avila.com.gamematcher2.db.entities.Player

interface Sports{

    fun getIdentifier() : String

    fun getName() : String

    fun getPositions() : List<String>

    fun sortByPositions(players : List<Player>) : List<Team>

    fun getPlayerPositions(selectedPositions : Set<String>)

    fun convertPositionsToStr(selectedPositions: MutableSet<String>) : String

    fun convertPositionsToMutableSet(positions : String) : MutableSet<String>
}