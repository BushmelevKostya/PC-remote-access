package server

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.DataInputStream
import java.io.DataInputStream.readUTF
import java.io.DataOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.net.Socket

class ClientThread(_clientSocket: Socket, _in : InputStream, _out : OutputStream) : Thread() {
    private val clientSocket : Socket
    private val input : InputStream
    private val output : OutputStream
    private val logger : Logger = LoggerFactory.getLogger(ClientThread::class.java)
    init {
        clientSocket = _clientSocket
        input = _in
        output = _out
    }

    override fun run() {
        val dis = DataInputStream(input)
        val dos = DataOutputStream(output)

        val message = "Welcome to Server"
        dos.writeUTF(message)

        logger.info(readUTF(dis))
        clientSocket.close()
    }
}