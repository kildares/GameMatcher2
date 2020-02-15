package matcher.game.silveira.avila.com.gamematcher2.repository

import androidx.lifecycle.LiveData
import matcher.game.silveira.avila.com.gamematcher2.db.dao.MatchDao
import matcher.game.silveira.avila.com.gamematcher2.db.entities.Match
import javax.inject.Inject

class MatchRepository @Inject constructor(matchDao : MatchDao){

    var matchDao : MatchDao = matchDao


    fun getMatchLiveDataList() : LiveData<List<Match>>{
        return matchDao.findAll()
    }

    fun addMatchToLiveData(match : Match){
        var id = matchDao.findMaxId()
        id++;
        match.id = id
        matchDao.insertMatch(listOf(match))
    }
}
