package com.example.demo.util

import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken

object GsonUtil {

    val gson = Gson()

    private val jsonParser = JsonParser()

    //json format data convert java bean
    inline fun <reified T> toBean(json: String): T = gson.fromJson(json, T::class.java)

    fun toMap() {

    }

    inline fun <reified T> toList(json: String): T = gson.fromJson<T>(json, object : TypeToken<T>() {}.type)


    //get single value (use key-value) search in json
    fun getSingleValue(json: String, key: String): String = jsonParser.parse(json).asJsonObject[key].asString

    //means upward
    fun getValue(json: String, keys: List<String>): List<String> {
        val rawJsonArray = jsonParser.parse(json).asJsonObject
        val jsonList = mutableListOf<String>()
        repeat(keys.size) {
            jsonList.add(rawJsonArray[keys[it]].asString)
        }
        return jsonList
    }

}