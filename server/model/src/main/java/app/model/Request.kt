package app.model

import java.io.Serializable

data class Request(var code: app.model.Code = app.model.Code.DEFAULT, var message: String = "") : Serializable
