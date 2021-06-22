package com.example.demo.service

import com.example.demo.mapper.UserMapper
import com.example.demo.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {

    @Autowired
    lateinit var userMapper: UserMapper

    fun registerUser(user: User) = userMapper.registerUser(user)

    fun login(user: User) = userMapper.login(user)

    fun getUserId(username: String) = userMapper.getUserId(username)

    fun updatePassword(user: User) = userMapper.updatePassword(user)
}