package com.mmpocket.ideapocket.application

import com.mmpocket.ideapocket.domain.memo.*
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service

@Service
class MemoProvider(
    private val repository: MemoRepository
){
    fun findAll(userId: String): List<Memo> {
        return repository.findAll(MemoSpecification(MemoFilter(userId = userId)).build())
    }

    fun findById(id: String): Memo {
        val memoId = MemoId(id.toLong())
        val memo = repository.findById(memoId).orElseThrow { NotFoundException() }
        return memo
    }
}