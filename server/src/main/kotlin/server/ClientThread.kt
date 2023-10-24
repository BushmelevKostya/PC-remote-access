package server

import app.model.Code
import app.model.Request
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.InputStream
import java.io.ObjectInputStream
import java.io.OutputStream
import java.net.Socket

class ClientThread(_clientSocket: Socket, _in: InputStream, _out: OutputStream) : Thread() {
    private val clientSocket: Socket
    private val input: InputStream
    private val output: OutputStream
    private val logger: Logger = LoggerFactory.getLogger(ClientThread::class.java)

    init {
        clientSocket = _clientSocket
        input = _in
        output = _out
    }

    override fun run() {
        val dis = DataInputStream(input)
        val dos = DataOutputStream(output)
        val ois = ObjectInputStream(dis)
        val request: Request = ois.readObject() as Request
        logger.info("message: " + request.message)
        logger.info("code: " + request.code)
        if (request.code == Code.ROTATE || request.code == Code.TURNOFF) {
            val runtime = Runtime.getRuntime()
            runtime.exec("DisplaySwitch.exe /clone")
        }
        clientSocket.close()
    }
}