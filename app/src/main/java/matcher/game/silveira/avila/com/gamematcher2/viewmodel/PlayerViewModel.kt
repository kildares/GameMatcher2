package matcher.game.silveira.avila.com.gamematcher2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import matcher.game.silveira.avila.com.gamematcher2.db.entities.Player
import matcher.game.silveira.avila.com.gamematcher2.repository.PlayerRepository
import javax.inject.Inject

class PlayerViewModel @Inject constructor(private val playerRepository : PlayerRepository) : ViewModel() {

    var playerLiveData : LiveData<List<Player>> = playerRepository.getAllPlayers();
    lateinit var currentPlayers : List<Player>

    fun loadPlayersByMatchId(matchId : Int){
        currentPlayers = playerLiveData.value?.filter { it.matchId == matchId } ?: emptyList()
    }

    fun addPlayer(playerName : String, playerPosition : String, matchId : Int){

        viewModelScope.launch (Dispatchers.IO){
            playerRepository.addPlayer(Player(0, playerName, playerPosition, matchId))
        }
    }

    fun updatePlayer(id : Int, name : String, position : String, matchId : Int){
        viewModelScope.launch(Dispatchers.IO) {
            playerRepository.updatePlayer(Player(id, name, position, matchId))
        }
    }

}
