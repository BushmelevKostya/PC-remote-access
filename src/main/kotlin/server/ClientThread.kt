package server

import java.net.ServerSocket

class ClientThread(_clientSocket: ServerSocket) : Thread() {
    private val clientSocket : ServerSocket
    init {
        clientSocket = _clientSocket
    }

}