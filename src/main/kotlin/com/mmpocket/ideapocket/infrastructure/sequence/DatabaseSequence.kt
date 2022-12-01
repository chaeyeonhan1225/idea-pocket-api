package com.mmpocket.ideapocket.infrastructure.sequence

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "DatabaseSequence")
class DatabaseSequence(
    @Id
    @Column(length = 36)
    val id: String,

    val seq: Long = 1
) {
}