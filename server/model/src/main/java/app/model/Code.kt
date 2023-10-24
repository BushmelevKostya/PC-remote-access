package app.model

enum class Code(val code: Int) {
    DEFAULT(0),
    MESSAGE(1),
    ROTATE(2),
    TURNOFF(3),
    CORRECT(200),
    WRONG(400)
}