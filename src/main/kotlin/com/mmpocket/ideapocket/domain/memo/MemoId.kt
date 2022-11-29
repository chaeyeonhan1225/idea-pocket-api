package com.mmpocket.ideapocket.domain.memo

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import java.io.Serializable
import javax.persistence.Access
import javax.persistence.AccessType
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class MemoId @JsonCreator(mode = JsonCreator.Mode.DELEGATING) constructor(
    @Access(AccessType.FIELD)
    @Column
    @get:JsonValue
    val value: Long
) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MemoId

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}


