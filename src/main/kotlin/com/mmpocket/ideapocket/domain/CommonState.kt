package com.mmpocket.ideapocket.domain

enum class CommonState(
    val value: Int,

    val label: String
) {
    ACTIVE(1, "ACTIVE"),

    INACTIVE(9, "INACTIVE"),

    DELETED(0, "DELETED");

    companion object {
        private val values = values()
        fun parse(value: Int) = values.first { it.value == value }
    }
}