package com.mmpocket.ideapocket.application

import com.mmpocket.ideapocket.domain.directory.DirectoryRepository
import com.mmpocket.ideapocket.domain.memo.*
import com.mmpocket.ideapocket.domain.service.SequenceGenerator
import com.mmpocket.ideapocket.domain.user.UserId
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MemoApplication(
    private val sequenceGenerator: SequenceGenerator,
    private val repository: MemoRepository,
    private val directoryRepository: DirectoryRepository
) {
    fun createMemo(param: MemoParam, userId: String): Memo {
        val memoId = MemoId(sequenceGenerator.generate(Memo::class.java.simpleName))
        val memo = Memo(id = memoId, param = param, userId = UserId(value = userId.toLong()))
        repository.save(memo)
        return memo;
    }

    fun updateMemo(id: String, param: MemoParam): Memo {
        val memo = getMemoById(id)
        memo.update(param)
        return repository.save(memo)
    }

    fun moveMemoDirectory(memoId: String, directoryId: String): Memo {
        val memo = getMemoById(memoId)

        val directoryId = DirectoryId(directoryId.toLong())
        directoryRepository.findById(directoryId).orElseThrow { NotFoundException()  }

        memo.updateDirectoryId(directoryId)
        return repository.save(memo)
    }

    fun deleteMemo(id: String): Boolean {
        val memo = getMemoById(id)
        memo.delete()
        repository.save(memo)
        return true
    }

    fun getMemoById(id: String): Memo {
        val memoId = MemoId(id.toLong())
        return repository.findById(memoId).orElseThrow { NotFoundException() }
    }
}