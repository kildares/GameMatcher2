package matcher.game.silveira.avila.com.gamematcher2.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import matcher.game.silveira.avila.com.gamematcher2.R
import matcher.game.silveira.avila.com.gamematcher2.activity.MainActivity
import matcher.game.silveira.avila.com.gamematcher2.di.Injectable
import matcher.game.silveira.avila.com.gamematcher2.di.MatchViewModelFactory
import matcher.game.silveira.avila.com.gamematcher2.domain.SportsFacade
import matcher.game.silveira.avila.com.gamematcher2.viewmodel.MatchViewModel
import java.time.DateTimeException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class CreateMatchFragment : Fragment(), Injectable {

    private lateinit var mNameEditText: EditText
    private lateinit var mSportSpinner : Spinner
    private lateinit var mLocationEditText : EditText
    private lateinit var mDateEditText : EditText
    private lateinit var mSaveButton : Button

    private lateinit var matchViewModel : MatchViewModel

    private lateinit var mSportsList : List<String>

    @Inject
    lateinit var viewModelFactory : MatchViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_create_match, container, false)

        mNameEditText = view.findViewById(R.id.et_create_match_name)
        mLocationEditText = view.findViewById(R.id.et_create_match_location)
        mDateEditText = view.findViewById(R.id.et_create_match_date)
        mSaveButton = view.findViewById(R.id.bt_save_match)
        mSportSpinner = view.findViewById(R.id.sp_sport_type)

        mSportsList = SportsFacade.getAvailableSports()

        mSportSpinner.adapter = ArrayAdapter<String>(activity!!, R.layout.item_sport, mSportsList)
        mSportSpinner.setSelection(0)

        mSaveButton.setOnClickListener{

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
        val intent = Intent(activity, MainActivity::class.java)
        startActivity(intent)
    }

    private fun createMatch() : Boolean {
        val name = mNameEditText.text.toString()
        val location = mLocationEditText.text.toString()
        val date = mDateEditText.text.toString()
        val sport = mSportsList[mSportSpinner.selectedItemPosition]

        if(name.isNotEmpty() && location.isNotEmpty() && isValidDate(date)){
            matchViewModel.createMatch(name, location, date, SportsFacade.getSportIdentifierByName(sport))
            return true
        } else {
            Toast.makeText(context, "Please input all fields correctly",Toast.LENGTH_SHORT).show();
            return false
        }
    }

    private fun isValidDate(date: String): Boolean {
        return try{
            LocalDate.of(
                Integer.valueOf(date.substring(4)),
                Integer.valueOf(date.substring(2, 4)),
                Integer.valueOf(date.substring(0, 2))
            ).format(DateTimeFormatter.ofPattern("dd/MM/yy"))
            true
        } catch (e : DateTimeException){
            false
        }
    }

}
