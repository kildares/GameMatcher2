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

    fun addPlayer(playerName : String, positions : String, matchId : Int){

        viewModelScope.launch (Dispatchers.IO){
            playerRepository.addPlayer(Player(0, playerName,  matchId, positions))
        }
    }

    fun updatePlayer(id : Int, name : String, positions : String, matchId : Int){
        viewModelScope.launch(Dispatchers.IO) {
            playerRepository.updatePlayer(Player(id, name, matchId, positions ))
        }
    }

    fun removePlayer(position: Int){
        viewModelScope.launch(Dispatchers.IO) {
            playerRepository.removePlayer(playerLiveData.value!![position])
        }
    }

}
