package matcher.game.silveira.avila.com.gamematcher2.sports

import matcher.game.silveira.avila.com.gamematcher2.db.entities.Player

object FootballSeven : Sports {

    override fun getIdentifier() = "FB7"

    override fun getName() = "Football Seven"

    override fun getPositions(): List<String> {
        return Position.values().map { it.name }
    }

    override fun sortByPositions(players : List<Player>) : List<Team>{
        return listOf(Team(listOf("")))
    }

    override fun getPlayerPositions(selectedPositions: Set<String>) {

    }

    override fun convertPositionsToStr(selectedPositions: MutableSet<String>) : String{

        return if(selectedPositions.contains(Position.Goal.name)) "1" else "0" +
        if(selectedPositions.contains(Position.Defense.name)) "1" else "0" +
        if(selectedPositions.contains(Position.Midfield.name)) "1" else "0" +
        if(selectedPositions.contains(Position.Side.name)) "1" else "0" +
        if(selectedPositions.contains(Position.Attack.name)) "1" else "0"
    }

    override fun convertPositionsToMutableSet(positions: String): MutableSet<String> {

        val set = mutableSetOf<String>()
        if(positions.substring(0,1) == "1") set.add(Position.Goal.name)
        if(positions.substring(1,2) == "1") set.add(Position.Defense.name)
        if(positions.substring(2,3) == "1") set.add(Position.Midfield.name)
        if(positions.substring(3,4) == "1") set.add(Position.Side.name)
        if(positions.substring(4,5) == "1") set.add(Position.Attack.name)
        return set
    }

    private enum class Position(val code : Byte){
        Goal(1),
        Defense(2),
        Midfield(4),
        Side(8),
        Attack(16)
    }
}