package com.mmpocket.ideapocket.infrastructure.sequence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface DataBaseSequenceRepository: CrudRepository<DatabaseSequence, String> {
    @Modifying
    @Query("Update DatabaseSequence set seq = seq + :count Where id = :id")
    fun updateSeq(@Param("id") id: String, @Param("count") count: Long)
}