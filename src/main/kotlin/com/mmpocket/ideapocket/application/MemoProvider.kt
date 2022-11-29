package com.mmpocket.ideapocket.application

import com.mmpocket.ideapocket.domain.memo.Memo
import com.mmpocket.ideapocket.domain.memo.MemoId
import com.mmpocket.ideapocket.domain.memo.MemoRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service

@Service
class MemoProvider(
    private val repository: MemoRepository
){
    fun findAll(): List<Memo> {
        return repository.findAll()
    }

    fun findById(id: String): Memo {
        val memoId = MemoId(id.toLong())
        val memo = repository.findById(memoId).orElseThrow { NotFoundException() }
        return memo
    }
}