package example.getpassengers

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    val linearLayout: LinearLayout
        get() = findViewById(R.id.linearLayout)

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            val data = activityResult.data
            val fName = data?.getStringExtra("first_name") ?: ""
            val lName = data?.getStringExtra("last_name") ?: ""
            val phoneNumber = data?.getStringExtra("phone_number") ?: ""
            addPassView(Passenger(lName, fName, phoneNumber))
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun addPassView(newPass: Passenger) {
        val textView = TextView(this)
        textView.text = newPass.toString()
        textView.textSize = 14F
        textView.setTextColor(Color.BLUE)
        linearLayout.addView(textView)

    }
    fun startSecond(v : View) {
        startForResult.launch(
            Intent(this,
            GetPassengers::class.java)
        )
    }

}
class Passenger(val fName:String, val lName: String, val phone: String) {
    override fun toString () : String {
        return "$fName $lName $phone"
    }
}