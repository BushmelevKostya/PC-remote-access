package server

import java.net.ServerSocket
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.net.Socket

class Server {
    private val port = 9000;

    private val logger: Logger = LoggerFactory.getLogger(Server::class.java)

    fun run() {
        val serverSocket = ServerSocket(port)
        logger.info("Server is started")

        while (true) {
            val server : Socket = serverSocket.accept();
            logger.info("New connection: ${server.inetAddress.hostAddress}")

            val clientThread = ClientThread(server, server.getInputStream(), server.getOutputStream())
            clientThread.start()
        }
    }
}