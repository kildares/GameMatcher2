package matcher.game.silveira.avila.com.gamematcher2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import matcher.game.silveira.avila.com.gamematcher2.db.entities.Match
import matcher.game.silveira.avila.com.gamematcher2.db.entities.Player
import matcher.game.silveira.avila.com.gamematcher2.repository.MatchRepository
import matcher.game.silveira.avila.com.gamematcher2.repository.PlayerRepository
import javax.inject.Inject

class MatchViewModel @Inject constructor(private val matchRepository : MatchRepository) : ViewModel() {

    val matchLiveData : LiveData<List<Match>> = matchRepository.getMatchLiveDataList();

    fun createMatch(name : String, location : String, date : String) : Match{
        val match = Match(name, location, date)
        viewModelScope.launch(Dispatchers.IO) {
            matchRepository.addMatchToLiveData(match)
        }
        return match
    }

}
