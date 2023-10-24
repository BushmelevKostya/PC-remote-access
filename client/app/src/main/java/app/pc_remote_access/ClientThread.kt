package app.pc_remote_access

import java.io.DataOutputStream
import java.io.OutputStream
import java.net.InetAddress
import java.net.Socket

class ClientThread(_serverIPAddress : String, _port : Int, _codeCommand : Int, _message : String) : Thread() {
    private val serverIPAddress : String
    private val port : Int
    private val codeCommand : Int
    private val message : String
    private val codeMessage : Int = 1
    private val codeRotate : Int = 2
    private val codeOff : Int = 3

    init {
        serverIPAddress = _serverIPAddress
        port = _port
        codeCommand = _codeCommand
        message = _message
    }
    override fun run() {
        val inetAddress: InetAddress = InetAddress.getByName(serverIPAddress)
        val socket = Socket(inetAddress, port)
        val output: OutputStream = socket.getOutputStream()
        val dos: DataOutputStream = DataOutputStream(output)
        when (codeCommand) {
            codeMessage -> {
                dos.write(codeMessage)
                val outMsg: ByteArray = message.toByteArray()
                dos.write(outMsg)
            }

            codeRotate -> dos.write(codeRotate)
            codeOff -> dos.write(codeOff)
        }
    }
}