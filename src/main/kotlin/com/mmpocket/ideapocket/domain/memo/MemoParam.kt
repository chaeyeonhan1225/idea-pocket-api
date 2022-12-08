package com.mmpocket.ideapocket.domain.memo

data class MemoParam(
    val title: String,
    val content: String,
    val tags: List<String>
)
