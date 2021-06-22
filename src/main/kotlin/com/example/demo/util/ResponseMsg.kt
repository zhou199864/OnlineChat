package com.example.demo.util


/**
 * 数据统一包装类
 */
data class ResponseMsg(val status: Int, val msg: String, val obj: Any?)

// 提供TopFunction 建造一个ResponseMsg对象
fun buildOkResponseMsg(msg: String = "non", obj: Any? = null): ResponseMsg {
    return ResponseMsg(200, msg, obj)
}

fun buildErrorResponseMsg(msg: String = "non", obj: Any? = null): ResponseMsg {
    return ResponseMsg(500, msg, obj)
}