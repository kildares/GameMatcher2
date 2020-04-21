package silveira.avila.com.gamematcher2.repository

import androidx.lifecycle.LiveData
import silveira.avila.com.gamematcher2.db.dao.MatchDao
import silveira.avila.com.gamematcher2.db.entities.Match
import javax.inject.Inject

class MatchRepository @Inject constructor(private var matchDao: MatchDao){

    val matchLiveData : LiveData<List<Match>> = matchDao.findAll();

    fun getMatchLiveDataList() : LiveData<List<Match>>{
        return matchLiveData
    }

    fun addMatchToLiveData(match : Match){
        var id = matchDao.findMaxId()
        id++;
        match.id = id
        matchDao.insertMatch(listOf(match))
    }

    fun removeMatch(match : Match){
        matchDao.deleteMatch(match)
    }

}
