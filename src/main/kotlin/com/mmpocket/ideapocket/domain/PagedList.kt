package com.mmpocket.ideapocket.domain

import org.springframework.data.domain.Page

data class PagedList<T>(
    private val page: Page<T>
) {
    val content: List<T>
        get() = page.content
    val pageInfo: PageInfo
        get() = PageInfo(page.hasNext(), page.hasPrevious())
    val totalCount: Long
        get() = page.totalElements
}

data class PageInfo(
    val hasNextPage: Boolean,
    val hasPreviousPage: Boolean
)
