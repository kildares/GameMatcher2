package silveira.avila.com.gamematcher2.match.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import silveira.avila.com.gamematcher2.db.entities.Match
import silveira.avila.com.gamematcher2.repository.MatchRepository
import javax.inject.Inject

class MatchViewModel @Inject constructor(private val matchRepository : MatchRepository) : ViewModel() {

    val matchLiveData : LiveData<List<Match>> = matchRepository.getMatchLiveDataList();

    fun createMatch(name : String, location : String, date : String, sport : String) : Match{
        val match = Match(name = name, location = location, date = date, sport = sport)
        viewModelScope.launch(Dispatchers.IO) {
            matchRepository.addMatchToLiveData(match)
        }
        return match
    }

    fun removeMatch(position : Int){
        Log.d("MatchViewModel", "Removing Match Id: ${matchLiveData.value!![position].id}")
        viewModelScope.launch(Dispatchers.IO) {
            matchRepository.removeMatch(matchLiveData.value!![position])
        }
    }

}
