package matcher.game.silveira.avila.com.gamematcher2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import matcher.game.silveira.avila.com.gamematcher2.db.Match
import matcher.game.silveira.avila.com.gamematcher2.repository.MatchRepository
import javax.inject.Inject

class MatchDetailViewModel @Inject constructor(private val matchRepository : MatchRepository) : ViewModel(){

    val liveData : LiveData<List<Match>> = matchRepository.getMatchLiveDataList()

}