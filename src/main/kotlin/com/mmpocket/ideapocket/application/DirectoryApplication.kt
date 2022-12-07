package com.mmpocket.ideapocket.application

import com.mmpocket.ideapocket.domain.directory.Directory
import com.mmpocket.ideapocket.domain.directory.DirectoryParam
import com.mmpocket.ideapocket.domain.directory.DirectoryRepository
import com.mmpocket.ideapocket.domain.memo.DirectoryId
import com.mmpocket.ideapocket.domain.service.SequenceGenerator
import com.mmpocket.ideapocket.domain.user.UserId
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class DirectoryApplication(
    private val repository: DirectoryRepository,
    private val sequenceGenerator: SequenceGenerator
) {
    fun createDirectory(param: DirectoryParam): Directory {
        val directoryId = DirectoryId(sequenceGenerator.generate(Directory::class.java.simpleName))
        val userId = UserId(1)
        val directory = Directory(id = directoryId, userId = userId, param = param)
        return repository.save(directory)
    }

    fun updateDirectory(id: String, param: DirectoryParam): Directory {
        val directory = getDirectoryById(id)
        directory.update(param)
        return repository.save(directory)
    }

    fun hideDirectory(id: String): Directory {
        val directory = getDirectoryById(id)
        directory.hide()
        return repository.save(directory)
    }

    fun deleteDirectory(id: String): Boolean {
        val directory = getDirectoryById(id)
        directory.delete()
        repository.save(directory)
        return true
    }

    private fun getDirectoryById(id: String): Directory {
        val directoryId = DirectoryId(id.toLong())
        return repository.findById(directoryId).orElseThrow { NotFoundException() }
    }
}