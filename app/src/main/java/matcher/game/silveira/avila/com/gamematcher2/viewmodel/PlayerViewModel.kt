package matcher.game.silveira.avila.com.gamematcher2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import matcher.game.silveira.avila.com.gamematcher2.db.entities.Player
import matcher.game.silveira.avila.com.gamematcher2.repository.PlayerRepository
import javax.inject.Inject

class PlayerViewModel @Inject constructor(private val playerRepository : PlayerRepository) : ViewModel() {

    var playerLiveData : LiveData<List<Player>>? = null;

    fun loadPlayersByMatchId(matchId : Int){
        playerLiveData = playerRepository.getPlayerLiveDataList(matchId)
    }
}
