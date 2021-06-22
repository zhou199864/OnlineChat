package com.example.demo.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.data.redis.core.HashOperations
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component

@Component
class RedisConfig {

    @Autowired
    lateinit var redisTemplate: RedisTemplate<String, String>

    @Bean
    fun hashOperations(): HashOperations<String, String, String> = redisTemplate.opsForHash()
}