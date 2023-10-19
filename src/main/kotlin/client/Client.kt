package client

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.InputStream
import java.io.OutputStream
import java.net.Socket

class Client(_serverAddress : String, _port : Int) {
    private val serverAddress : String
    private val port : Int
    private val logger : Logger = LoggerFactory.getLogger(Client::class.java)
    init {
        serverAddress = _serverAddress
        port = _port
    }
    fun send(message : String) {
        val clientSocket = Socket(serverAddress, port)

        val input : InputStream = clientSocket.getInputStream()
        val output : OutputStream = clientSocket.getOutputStream()

        val messageBytes = message.toByteArray()
        output.write(messageBytes)
        output.flush()
        logger.info("Message send to server: $message")

        val getMessage = input.readAllBytes()
        logger.info(getMessage.toString())

        clientSocket.close()
    }
}