package com.example.demo.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.TimeUnit


@Service
class RedisService {

    @Autowired
    private lateinit var redisTemplate: RedisTemplate<String, String>

    //是否存在key
    fun hasKey(key: String) = redisTemplate.hasKey(key)

    //修改key 若newKey已存在直接覆盖
    fun renameKey(oldKey: String, newKey: String) = redisTemplate.rename(oldKey, newKey)

    //newKey不存在时才能修改
    fun renameIfAbsent(oldKey: String, newKey: String) = redisTemplate.renameIfAbsent(oldKey, newKey)

    //删除key
    fun deleteKey(key: String) = redisTemplate.delete(key)

    //删除多个key
    fun deleteKeys(keys: Set<String>) = redisTemplate.delete(keys)

    //设置key的生命周期
    fun expireKey(key: String, time: Long, timeUnit: TimeUnit) = redisTemplate.expire(key, time, timeUnit)

    //指定key在指定的日期过期
    fun expireKeyAt(key: String, date: Date) = redisTemplate.expireAt(key, date)

    //查询key生命周期
    fun getKeyExpire(key: String, timeUnit: TimeUnit) = redisTemplate.getExpire(key, timeUnit)

    //设置key永久有效
    fun persisKey(key: String) = redisTemplate.persist(key)

}