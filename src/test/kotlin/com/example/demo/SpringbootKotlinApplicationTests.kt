package com.example.demo

import com.example.demo.model.User
import com.example.demo.service.RedisService
import com.example.demo.service.UserService
import com.example.demo.util.GsonUtil
import com.example.demo.util.UserToken
import com.example.demo.util.generalToken
import com.google.gson.Gson
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.HashOperations

@SpringBootTest
class SpringbootKotlinApplicationTests {

    @Autowired
    lateinit var hashOperations: HashOperations<String, String, String>

    @Autowired
    lateinit var redisService: RedisService

    @Autowired
    lateinit var userToken: UserToken

    @Autowired
    lateinit var userService: UserService

    @Test
    fun contextLoads() {
//        println(generalToken())
//        hashOperations.put("kt1","k1","v1")
//        hashOperations.put("kt1","k2","v2")
//        hashOperations.put("kt1","k3","v3")
//        hashOperations.put("kt1","k3","v3")
//        println(hashOperations.entries("kt1").containsValue("v5"))
//        println(userToken.tokenHasContains("v1"))
//        repeat(10) {
//            userToken.addToken("test$it", generalToken())
//        }
//        println(userToken.userHasContainer("test1"))
//        println(userToken.tokenHasContains("vw271ze7se2o8yh6e4y83c14255w6372"))
        val json = """
            {"name":"zyique","sex":"Man","age":22,"address":"JXSR"}
        """.trimIndent()

//        println(GsonUtil.getSingleValue(json, "address"))
//        println(GsonUtil.getValue(json, listOf("name","age")))

//        println(userService.updatePassword(User("zmfa", "zmfyayaya")))
//        println(userToken.getUsernameByToken("vw271ze7se2o8yh6e4y83c14255w6372"))

    }

}
