package com.example.demo.controller

import com.example.demo.service.WebSocketServer
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/chat")
class ChatController {

    @GetMapping("/push/{msg}/{toUser}")
    fun pushMessage(@PathVariable msg: String, @PathVariable toUser: String) {
        WebSocketServer.sendInfo(msg, toUser)
    }
}