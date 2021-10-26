package masson.reynoso.contador

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

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

        btn_borrar.setOnClickListener {
            val alertDialog: AlertDialog? = this?.let {
                val builder = AlertDialog.Builder(it)
                builder.apply {
                    setPositiveButton("borrar",
                        DialogInterface.OnClickListener { dialog, id ->
                            cuenta = 0
                            tv_cuenta.setText("$cuenta")
                        })
                    setNegativeButton("cancelar",
                        DialogInterface.OnClickListener { dialog, id ->
                            // User cancelled the dialog
                        })
                }
                // Set other dialog properties
                builder?.setMessage("¿Seguro que desea eliminar la cuenta?")
                    .setTitle("ALERTA")


                // Create the AlertDialog
                builder.create()
            }

            alertDialog?.show()
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