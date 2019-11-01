package matcher.game.silveira.avila.com.gamematcher2.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import matcher.game.silveira.avila.com.gamematcher2.CreateMatchActivity
import matcher.game.silveira.avila.com.gamematcher2.MainActivity
import matcher.game.silveira.avila.com.gamematcher2.R
import matcher.game.silveira.avila.com.gamematcher2.di.Injectable
import matcher.game.silveira.avila.com.gamematcher2.di.MatchViewModelFactory
import matcher.game.silveira.avila.com.gamematcher2.viewmodel.MatchViewModel
import javax.inject.Inject

class CreateMatchFragment : Fragment(), Injectable {

    private lateinit var nameEditText: EditText
    private lateinit var locationEditText : EditText
    private lateinit var dateEditText : EditText
    private lateinit var saveButton : Button

    private lateinit var matchViewModel : MatchViewModel

    @Inject
    lateinit var viewModelFactory : MatchViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_create_match, container, false)

        nameEditText = view.findViewById(R.id.et_match_name)
        locationEditText = view.findViewById(R.id.et_match_location)
        dateEditText = view.findViewById(R.id.et_match_date)
        saveButton = view.findViewById(R.id.bt_save_match)

        saveButton.setOnClickListener{

            val createdMatch = createMatch()
            if(createdMatch){
                startMainActivity()
            }
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        matchViewModel = ViewModelProviders.of(this, this.viewModelFactory).get(MatchViewModel::class.java)
    }


    private fun startMainActivity() {
        var intent = Intent(activity, MainActivity::class.java)
        startActivity(intent)
    }

    private fun createMatch() : Boolean {
        val name = nameEditText.text.toString()
        val location = locationEditText.text.toString()
        val date = dateEditText.text.toString()

        if(name.isNotEmpty() && location.isNotEmpty() && date.isNotEmpty()){
            matchViewModel.createMatch(name, location, date)
            return true
        } else {
            Toast.makeText(context, "There is missing information",Toast.LENGTH_SHORT).show();
            return false
        }
    }

}
