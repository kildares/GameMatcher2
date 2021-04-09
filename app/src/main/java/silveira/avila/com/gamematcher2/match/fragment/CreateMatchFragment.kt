package silveira.avila.com.gamematcher2.match.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import silveira.avila.com.gamematcher2.R
import silveira.avila.com.gamematcher2.activity.MainActivity
import silveira.avila.com.gamematcher2.di.Injectable
import silveira.avila.com.gamematcher2.di.MatchViewModelFactory
import silveira.avila.com.gamematcher2.domain.SportsFacade
import silveira.avila.com.gamematcher2.match.viewmodel.MatchViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class CreateMatchFragment : Fragment(), Injectable {

    private lateinit var mNameEditText: EditText
    private lateinit var mSportSpinner : Spinner
    private lateinit var mLocationEditText : EditText
    private lateinit var mDateTextView : TextView
    private lateinit var mDatePickButton : Button
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
        mDateTextView = view.findViewById(R.id.tv_create_match_date_value)
        mDatePickButton = view.findViewById(R.id.bt_create_match_pick_date)
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

        view.findViewById<Toolbar>(R.id.tb_create_match).setNavigationOnClickListener{
            activity!!.finish()
        }

        mDatePickButton.setOnClickListener {

            val alertDialog = androidx.appcompat.app.AlertDialog.Builder(activity!!).create()
            val activityView = activity!!.layoutInflater.inflate(R.layout.date_picker, null)
            val datePicker = view.findViewById<DatePicker>(R.id.dp_date_picker)
            alertDialog.setView(view)
            activityView.findViewById<TextView>(R.id.tv_match_date).setOnClickListener{_ ->
                mDateTextView.text = "${datePicker.dayOfMonth.toString().padStart(2, '0')}/${(datePicker.month + 1).toString().padStart(2, '0')}/${datePicker.year}"
                alertDialog.dismiss()
            }
            alertDialog.show()
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        matchViewModel = ViewModelProvider(this, this.viewModelFactory).get(MatchViewModel::class.java)
        //matchViewModel = ViewModelProviders.of(this, this.viewModelFactory).get(MatchViewModel::class.java)
    }


    private fun startMainActivity() {
        val intent = Intent(activity, MainActivity::class.java)
        startActivity(intent)
    }

    private fun createMatch() : Boolean {
        val name = mNameEditText.text.toString()
        val location = mLocationEditText.text.toString()
        val date = mDateTextView.text.toString()
        val sport = mSportsList[mSportSpinner.selectedItemPosition]

        if(name.isNotEmpty() && location.isNotEmpty() && isValidDate(date)){
            matchViewModel.createMatch(name, location, date.replace("",""), SportsFacade.getSportIdentifierByName(sport))
            return true
        } else {
            Toast.makeText(context, "Please input all fields correctly",Toast.LENGTH_SHORT).show();
            return false
        }
    }

    private fun isValidDate(date: String): Boolean {
        return try{
            LocalDate.of(
                Integer.valueOf(date.substring(6)),
                Integer.valueOf(date.substring(3, 5)),
                Integer.valueOf(date.substring(0, 2))
            ).format(DateTimeFormatter.ofPattern("dd/MM/yy"))
            true
        } catch (e : Exception){
            false
        }
    }

}
