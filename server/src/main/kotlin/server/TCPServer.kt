package server

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.ServerSocket
import java.net.Socket

class TCPServer {
    private val port : Int = 9875

    private val logger: Logger = LoggerFactory.getLogger(Server::class.java)

    fun run() {
        val serverSocket = ServerSocket(port)
        logger.info("Server is started on address: " + InetAddress.getLocalHost())

        while (true) {
            val server : Socket = serverSocket.accept();
            logger.info("New connection: ${server.inetAddress.hostAddress}")

            val clientThread = ClientThread(server, server.getInputStream(), server.getOutputStream())
            clientThread.start()
        }
    }
}