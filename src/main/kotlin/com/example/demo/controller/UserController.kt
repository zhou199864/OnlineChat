package com.example.demo.controller

import com.example.demo.model.User
import com.example.demo.service.UserService
import com.example.demo.util.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/user"])
class UserController {

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var userToken: UserToken

    @RequestMapping(value = ["/login"], method = [RequestMethod.POST])
    fun login(@RequestBody user: User): ResponseMsg {
        userService.login(user)?.let {
            val token = generalToken()
            userToken.addToken(user.username, token)
            return buildOkResponseMsg(msg = "login successful.", obj = mapOf("token" to token))
        }
        return buildErrorResponseMsg(msg = "login failed. please check it you account and password.")
    }

    @RequestMapping(value = ["/logout"], method = [RequestMethod.POST])
    fun logout(@RequestBody json: String): ResponseMsg {
        val token = GsonUtil.getSingleValue(json, "token")
        if (userToken.tokenHasContains(token)) {
            return buildOkResponseMsg("logout successful.")
        }
        return buildErrorResponseMsg("logout failed you must login before.")
    }

    @RequestMapping(value = ["/register"], method = [RequestMethod.POST])
    fun register(@RequestBody user: User): ResponseMsg {
        userService.getUserId(user.username) ?: userService.registerUser(user)?.let {
            return buildOkResponseMsg("register successful.")
        }
        return buildErrorResponseMsg(msg = "account is exists please change you account.")
    }

    @RequestMapping(value = ["/updateUserInfo"], method = [RequestMethod.POST])
    fun updateUserInfo(json: String): ResponseMsg {
        return buildOkResponseMsg("user information had changed.")
    }

    @RequestMapping(value = ["/updatePassword"], method = [RequestMethod.POST])
    fun updatePassword(@RequestBody json: String): ResponseMsg {
        val data = GsonUtil.getValue(json, listOf("token", "password"))
        data?.let {
            if (data.size < 2) buildErrorResponseMsg("param error please check it.")
            val username = userToken.getUsernameByToken(data[0])
            if (userToken.tokenHasContains(data[0])) {
                if (userService.updatePassword(User(username, data[1])) == 1L)
                    return buildOkResponseMsg("password had changed please login again.")
            }
            return buildErrorResponseMsg("token error please check it.")
        }
    }

    @RequestMapping(value = ["/getVerifyCode"], method = [RequestMethod.POST])
    fun getVerifyCode(): ResponseMsg {
        return buildOkResponseMsg("verify code sent successful please check you email.")
    }

}