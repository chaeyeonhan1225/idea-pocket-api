package com.mmpocket.ideapocket.application

import com.mmpocket.ideapocket.domain.PagedList
import com.mmpocket.ideapocket.domain.directory.Directory
import com.mmpocket.ideapocket.domain.directory.DirectoryFilter
import com.mmpocket.ideapocket.domain.directory.DirectoryRepository
import com.mmpocket.ideapocket.domain.directory.DirectorySpecification
import com.mmpocket.ideapocket.domain.memo.DirectoryId
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class DirectoryProvider(
    private val repository: DirectoryRepository
) {
    fun findAll(userId: String, page: Int?, size: Int?): PagedList<Directory> {
        val pageRequest = PageRequest.of(page?.minus(1) ?: 1, size ?: 10, Sort.by(Sort.Direction.DESC, "id"))
        return PagedList(repository.findAll(DirectorySpecification(DirectoryFilter(userId)).build(), pageRequest))
    }

    fun findById(id: String): Directory {
        val directoryId = DirectoryId(id.toLong())
        return repository.findById(directoryId).orElseThrow { NotFoundException() }
    }
}