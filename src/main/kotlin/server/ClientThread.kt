package server

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

class ClientThread(_clientSocket: Socket) : Thread() {
    private val clientSocket : Socket
    private val logger : Logger = LoggerFactory.getLogger(ClientThread::class.java)
    init {
        clientSocket = _clientSocket
    }

    override fun run() {
        val reader = BufferedReader(InputStreamReader(clientSocket.getInputStream()))
        val writer = PrintWriter(clientSocket.getOutputStream(), true)

        writer.println("Welcome to Server!")

        while (true) {
            val clientMessage = reader.readLine() ?: break
            logger.info(clientMessage)
            writer.println("Your message: $clientMessage")
        }
        clientSocket.close()
    }
}