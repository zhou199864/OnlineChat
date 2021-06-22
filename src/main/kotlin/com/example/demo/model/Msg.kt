package com.example.demo.model

data class Msg(
    val fromUser: String,
    val toUsername: String,
    val content: String,
    val isMass: Boolean,
    val sentType: String
)