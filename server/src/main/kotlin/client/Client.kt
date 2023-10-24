package client

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.net.Socket

class Client(_serverAddress : String, _port : Int) {
    private val serverAddress : String
    private val port : Int
    private val logger : Logger = LoggerFactory.getLogger(Client::class.java)
    private val clientSocket : Socket
    init {
        serverAddress = _serverAddress
        port = _port
        clientSocket = Socket(serverAddress, port)
    }
    fun send(message : String) {
        val input : InputStream = clientSocket.getInputStream()
        val dis = DataInputStream(input)
        val output : OutputStream = clientSocket.getOutputStream()
        val dos = DataOutputStream(output)

        dos.writeUTF(message)
        logger.info("Message send to server: $message")

        val getMessage = dis.readUTF()
        logger.info(getMessage)

        clientSocket.close()
    }
}