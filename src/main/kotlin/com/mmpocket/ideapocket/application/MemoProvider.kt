package com.mmpocket.ideapocket.application

import com.mmpocket.ideapocket.domain.memo.Memo
import com.mmpocket.ideapocket.domain.memo.MemoRepository
import org.springframework.stereotype.Service

@Service
class MemoProvider(
    private val repository: MemoRepository
){
    fun findAll(): List<Memo> {
        return repository.findAll()
    }
}