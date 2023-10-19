package server

import java.net.ServerSocket
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class Server {
    private val port = 9000;
    companion object {
        val logger: Logger = LoggerFactory.getLogger(Server::class.java)
    }

    fun run() {
        val serverSocket = ServerSocket(port)
        logger.info("Server is started")

        while (true) {
            val clientSocket = serverSocket.accept();
            logger.info("New connection: ${clientSocket.inetAddress.hostAddress}")

            val clientThread = ClientThread(clientSocket)
            clientThread.start()
        }
    }
}