package app.model

import java.io.Serializable

data class Response(var code: Code = Code.DEFAULT, var message: String = "") : Serializable
