package com.mmpocket.ideapocket.application

import com.mmpocket.ideapocket.domain.directory.Directory
import com.mmpocket.ideapocket.domain.directory.DirectoryRepository
import com.mmpocket.ideapocket.domain.memo.DirectoryId
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class DirectoryProvider(
    private val repository: DirectoryRepository
){
    fun findAll(): List<Directory> {
        return repository.findAll()
    }

    fun findById(id: String): Directory {
        val directoryId = DirectoryId(id.toLong())
        return repository.findById(directoryId).orElseThrow { NotFoundException() }
    }
}