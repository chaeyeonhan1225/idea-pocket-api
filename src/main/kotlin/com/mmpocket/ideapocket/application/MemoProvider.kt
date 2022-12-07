package com.mmpocket.ideapocket.application

import com.mmpocket.ideapocket.domain.PagedList
import com.mmpocket.ideapocket.domain.memo.*
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class MemoProvider(
    private val repository: MemoRepository
) {
    fun findAll(userId: String, page: Int?, size: Int?): PagedList<Memo> {
        val pageRequest = PageRequest.of(page?.minus(1) ?: 1, size ?: 10)
        return PagedList(repository.findAll(MemoSpecification(MemoFilter(userId = userId)).build(), pageRequest))
    }

    fun findById(id: String): Memo {
        val memoId = MemoId(id.toLong())
        return repository.findById(memoId).orElseThrow { NotFoundException() }
    }
}