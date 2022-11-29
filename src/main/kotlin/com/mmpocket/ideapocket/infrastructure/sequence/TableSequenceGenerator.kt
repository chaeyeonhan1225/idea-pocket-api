package com.mmpocket.ideapocket.infrastructure.sequence

import com.mmpocket.ideapocket.domain.service.SequenceGenerator
import org.springframework.stereotype.Service

@Service
class TableSequenceGenerator(private val repository: DataBaseSequenceRepository): SequenceGenerator {
    override fun generate(name: String): Long {
        return generateByBulk(name, 1).first()
    }

    fun generateByBulk(name: String, count: Long): List<Long> {
        if (repository.existsById(name)) {
            repository.updateSeq(name, count)
        } else {
            repository.save(DatabaseSequence(name, count))
        }

        val seq = repository.findById(name).orElseThrow { RuntimeException("Fail to generate id") }.seq

        return (seq - (count-1) .. seq).toList()
    }

}