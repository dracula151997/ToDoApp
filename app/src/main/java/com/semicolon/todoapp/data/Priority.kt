package com.semicolon.todoapp.data

/**
 *Created by Hassan Mohammed on 6/26/21
 */
enum class Priority {
    HIGH, MEDIUM, LOW;

    companion object {
        fun parse(priority: String): Priority {
            return when (priority) {
                "Low" -> LOW
                "Medium" -> MEDIUM
                "High" -> HIGH
                else -> LOW
            }
        }
    }
}