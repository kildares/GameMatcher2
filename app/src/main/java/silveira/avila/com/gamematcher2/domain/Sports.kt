package silveira.avila.com.gamematcher2.domain

import silveira.avila.com.gamematcher2.db.entities.Player

interface Sports{

    fun getIdentifier() : String

    fun getName() : String

    fun getPositions() : List<String>

    fun convertPositionsToStr(selectedPositions: MutableSet<String>) : String

    fun convertPositionsToMutableSet(positions : String) : MutableSet<String>

    fun isFormalAndShownIdentical(formalPosition: String, shownPosition: String): Boolean

    fun pickTeams(players : List<Player>, type : PickTeamOptions) : List<Team>
}