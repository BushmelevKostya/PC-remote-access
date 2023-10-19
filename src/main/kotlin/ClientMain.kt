import client.Client

fun main() {
    val serverAddress = "127.0.0.1"
    val port = 9000
    val message = "Hi, server!"
    val client = Client(serverAddress, port)
    client.send(message)
}