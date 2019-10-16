package matcher.game.silveira.avila.com.gamematcher2.repository

import androidx.lifecycle.LiveData
import matcher.game.silveira.avila.com.gamematcher2.db.Match
import matcher.game.silveira.avila.com.gamematcher2.db.MatchDao
import javax.inject.Inject

class MatchRepository @Inject constructor(matchDao : MatchDao){

    var matchDao : MatchDao = matchDao

    fun getMatchLiveDataList() : LiveData<List<Match>>{
        return matchDao.findAll()
    }

}