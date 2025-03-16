package example.getpassengers

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.ArrayList

class GetPassengers : AppCompatActivity() {
    // passList stores Passenger objects
    var passList: MutableList<Passenger> = ArrayList<Passenger>()
    val accumList: TextView
        get() = findViewById(R.id.accum_list)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_passengers)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // On button click, record the passenger information, create a new Passenger object,
    // and display the new passenger in the TextView
    fun enterPassenger(v : View) {
        // Set the vars to the respective EditText fields
        var textFirst = findViewById<EditText>(R.id.first_name)
        var textLast = findViewById<EditText>(R.id.last_name)
        var textPhone = findViewById<EditText>(R.id.phone_number)
        // Convert inputs to strings and create a new Passenger object
        var firstName = textFirst.getText().toString()
        var lastName = textLast.getText().toString()
        var phoneNumber = textPhone.getText().toString()
        var newPassenger = Passenger(firstName, lastName, phoneNumber)
        passList.add(newPassenger)

        // Display the new passenger in the TextView
        accumList.append("\n$newPassenger")

        // reset the text fields
        textFirst.text.clear()
        textLast.text.clear()
        textPhone.text.clear()
    }

    // On button click, return to the MainActivity and send the passenger list
    fun backToMain(v : View) {
        intent.let { passengerInfoIntent ->
            // Pack key value pairs into the intent object
            passengerInfoIntent.putExtra("COUNT", passList.size.toString())
            for (i in 1..passList.size){
                passengerInfoIntent.putExtra("PASS$i", passList[i-1].toString())
            }
            // Use setResult to send the loaded intent object (passengerInfoIntent) back to the MainActivity
            setResult(Activity.RESULT_OK, passengerInfoIntent)
            finish()
        }

    }



}
