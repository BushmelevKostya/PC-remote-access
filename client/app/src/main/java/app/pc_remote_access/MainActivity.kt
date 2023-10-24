package app.pc_remote_access

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import app.model.Code
import app.model.Request
import app.pc_remote_access.client.ClientThread
import java.io.DataOutputStream
import java.io.ObjectOutputStream
import java.net.InetAddress
import java.net.Socket

class MainActivity() : AppCompatActivity() {
    private lateinit var serverIPAddress: String
    private val port: Int = 9875
    private var message: String = ""
    private val codeMessage: Int = 1
    private val codeRotate: Int = 2
    private val codeOff: Int = 3
    private var codeCommand: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(view: View) {
        val etIPAddress: EditText = findViewById(R.id.edIPaddress)
        serverIPAddress = etIPAddress.text.toString()
        if (serverIPAddress.isEmpty()) {
            val msgToast: Toast = Toast.makeText(this, "Please write ip address!", Toast.LENGTH_SHORT)
            msgToast.show()
            return
        }
        val clientThread = ClientThread(serverIPAddress, port, codeCommand, message)

        when (view.id) {
            R.id.btnSMsg -> {
                if (!message.isEmpty()) {
                    codeCommand = codeMessage
                    clientThread.start()
                } else {
                    val msgToast : Toast = Toast.makeText(this, "Please write message!", Toast.LENGTH_SHORT)
                    msgToast.show()
                }
            }
            R.id.btnRotate -> {
                codeCommand = codeRotate
                clientThread.start()
            }
            R.id.btnPowerOff -> {
                codeCommand = codeOff
                clientThread.start()
            }
        }
    }
}