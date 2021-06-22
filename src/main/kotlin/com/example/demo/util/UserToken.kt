package com.example.demo.util

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.HashOperations
import org.springframework.stereotype.Service

@Service
class UserToken {

    @Autowired
    private lateinit var hashOperations: HashOperations<String, String, String>

    fun addToken(username: String, token: String) = hashOperations.put(GlobalVariable.REDIS_TOKEN_LIST, username, token)

    fun userHasContainer(username: String) = hashOperations.entries(GlobalVariable.REDIS_TOKEN_LIST).containsKey(username)

    fun tokenHasContains(token: String) = hashOperations.entries(GlobalVariable.REDIS_TOKEN_LIST).containsValue(token)

    fun getUsernameByToken(token: String): String = hashOperations.entries(GlobalVariable.REDIS_TOKEN_LIST).filter { it.value == token }.keys.iterator().next()

}