package com.example.demo.mapper

import com.example.demo.model.User
import org.apache.ibatis.annotations.Mapper

@Mapper
interface UserMapper {
    fun login(user: User): Long?
    fun registerUser(user: User): Long?
    fun getUserId(username: String): Long?
    fun updatePassword(user: User): Long
}