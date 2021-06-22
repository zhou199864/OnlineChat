package com.example.demo.util

import java.lang.Exception
import java.util.concurrent.locks.ReentrantLock

inline fun tryCatch(block: () -> Unit) {
    try {
        block()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

inline fun syncBlock(runBlock: () -> Unit, finalBlock: () -> Unit) {
    val lock = ReentrantLock()
    lock.lock()
    try {
        runBlock()
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        lock.unlock()
        finalBlock()
    }
}