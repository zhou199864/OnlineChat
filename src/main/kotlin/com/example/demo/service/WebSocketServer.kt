package com.example.demo.service

import com.example.demo.model.Msg
import com.example.demo.util.GsonUtil
import com.example.demo.util.syncBlock
import com.example.demo.util.tryCatch
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import javax.websocket.*
import javax.websocket.server.PathParam
import javax.websocket.server.ServerEndpoint


@ServerEndpoint("/server/{username}")
@Component
class WebSocketServer {
    companion object {
        private val logger = LoggerFactory.getLogger("WebSocketServer")
        private var onlineCount = 0
        private var webSocketMap = mutableMapOf<String, WebSocketServer>()

        fun sendInfo(msg: String, receiver: String) {
            if (webSocketMap.containsKey(receiver)) {
                webSocketMap[receiver]?.sendMessage(msg)
            } else {
                logger.info("user $receiver offline.")
            }
        }

    }

    private var session: Session? = null
    private var username = ""

    @OnOpen
    fun onOpen(session: Session, @PathParam("username") username: String) {
        this.session = session
        this.username = username
        if (webSocketMap.containsKey(username)) {
            //add
            webSocketMap.remove(username)
            webSocketMap[username] = this
        } else {
            webSocketMap[username] = this
        }
        syncBlock({ onlineCount++ }, { logger.info("user $username online, now chat room have $onlineCount people.") })

        //sendMessage
        sendMessage("connect successful.")

    }

    @OnMessage
    fun onMessage(message: String, session: Session) {
        if (message.isBlank()) return
        logger.info("user $username sent $message")
        //supply a flag to judgement sent ways.
        tryCatch {
            val msg = GsonUtil.toBean<Msg>(message)
            if (msg.isMass) {
                webSocketMap.forEach { (str, web) ->
                    if (str != msg.fromUser) {
                        web.sendMessage(msg.content)
                    }
                }
            } else {
                webSocketMap[msg.toUsername]?.sendMessage(msg.content)
                println("single sent.")
            }
//            when (msg.sentType) {
//                "text" -> {
//                }
//                "img" -> {
//                }
//                "file" -> {
//                }
//            }
        }
    }


    @OnClose
    fun onClose() {
        if (webSocketMap.containsKey(username)) {
            webSocketMap.remove(username)
            syncBlock({ onlineCount-- },
                { logger.info("user $username offline, now chat room have $onlineCount people.") })
        }
    }


    @OnError
    fun onError(session: Session, error: Throwable) {
        logger.warn("user $username error")
        error.printStackTrace()
    }

    fun sendMessage(message: String) {
        tryCatch { this.session?.basicRemote?.sendText(message) }
    }


}