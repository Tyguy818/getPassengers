// Tyler Hancock
package example.getpassengers

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    // TextView for the list of passengers
    val listText: TextView
        get() = findViewById(R.id.show_list)

    // Capture passenger information and append it
    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            val data = activityResult.data
            val count = ((data?.getStringExtra("COUNT") ?: "")).toInt()
            for (i in 1..count) {
                val passenger = data?.getStringExtra("PASS"+i) ?: ""
                listText.append("\n$passenger")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Reset the passenger list for new lists and switch to GetPassengers activity
    fun getList(v : View) {
        startForResult.launch(
            Intent(this,
            GetPassengers::class.java)
        )
        listText.setText("RETURNED PASSENGER LIST")
    }

}
class Passenger(val fName:String, val lName: String, val phone: String) {
    override fun toString () : String {
        return "$fName $lName $phone"
    }
}
