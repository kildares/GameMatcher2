package matcher.game.silveira.avila.com.gamematcher2.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import matcher.game.silveira.avila.com.gamematcher2.db.entities.Match
import matcher.game.silveira.avila.com.gamematcher2.db.dao.MatchDao
import javax.inject.Inject

class MatchRepository @Inject constructor(matchDao : MatchDao){

    var matchDao : MatchDao = matchDao


    fun getMatchLiveDataList() : LiveData<List<Match>>{
        return matchDao.findAll()
    }

    fun addMatchToLiveData(match : Match){

        matchDao.insertMatch(listOf(match))
    }
}
