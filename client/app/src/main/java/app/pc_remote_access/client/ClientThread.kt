package app.pc_remote_access.client

import app.model.Code
import app.model.Request
import java.io.DataOutputStream
import java.io.ObjectOutputStream
import java.net.InetAddress
import java.net.Socket

class ClientThread(_serverIPAddress : String, _port : Int, _codeCommand : Int, _message : String) : Thread() {
    private val serverIPAddress : String
    private val port : Int
    private val codeCommand : Int
    private val message : String

    init {
        serverIPAddress = _serverIPAddress
        port = _port
        codeCommand = _codeCommand
        message = _message
    }
    override fun run() {
        val inetAddress: InetAddress = InetAddress.getByName(serverIPAddress)
        val socket = Socket(inetAddress, port)
        val oos = ObjectOutputStream(DataOutputStream(socket.getOutputStream()))
        val request = Request()
        when (codeCommand) {
            Code.MESSAGE.code -> {
                request.code = Code.MESSAGE
                request.message = message
                oos.writeObject(request)
                oos.flush()
                oos.close()
            }
            Code.ROTATE.code -> {
                request.code = Code.ROTATE
                oos.writeObject(request)
                oos.flush()
                oos.close()
            }
            Code.TURNOFF.code -> {
                request.code = Code.TURNOFF
                oos.writeObject(request)
                oos.flush()
                oos.close()
            }
        }
    }
}