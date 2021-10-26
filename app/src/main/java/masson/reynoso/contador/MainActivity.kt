package masson.reynoso.contador

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var cuenta: Int = 0
    lateinit var tv_cuenta: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_suma: ImageButton = findViewById(R.id.btn_add)
        val btn_resta: ImageButton = findViewById(R.id.btn_substract)
        val btn_borrar: ImageButton = findViewById(R.id.btn_delete)
        tv_cuenta = findViewById(R.id.tv_count)
        val et_pichadas: EditText = findViewById(R.id.et_what)

        btn_suma.setOnClickListener {
            cuenta++
            tv_cuenta.setText("$cuenta")
        }

        btn_resta.setOnClickListener {
            cuenta--
            tv_cuenta.setText("$cuenta")
        }
    }

    override fun onPause() {
        super.onPause()

        val sharedPref = this?.getPreferences(Context.MODE_PRIVATE) ?: return
        val editor = sharedPref.edit()
        editor.putInt("contador", cuenta)
        editor.commit()
    }

    override fun onResume() {
        super.onResume()

        val sharedPref = this?.getPreferences(Context.MODE_PRIVATE)
        cuenta = sharedPref.getInt("contador", 0)
        tv_cuenta.setText("$cuenta")
    }
}