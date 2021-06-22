package com.example.demo.util


fun generalToken(length: Int = 32): String {
    var token = ""
    repeat(length) {
        when ((1..2).random()) {
            1 -> {
                token += ('a'..'z').random()
            }
            2 -> {
                token += (0..9).random()
            }
        }
    }
    return token
}