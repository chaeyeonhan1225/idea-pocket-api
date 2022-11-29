package com.mmpocket.ideapocket.domain.directory

import com.mmpocket.ideapocket.domain.CommonState
import com.mmpocket.ideapocket.domain.memo.DirectoryId
import com.mmpocket.ideapocket.domain.user.UserId
import javax.persistence.AttributeOverride
import javax.persistence.Column
import javax.persistence.Embedded
import javax.persistence.EmbeddedId
import javax.persistence.Entity

@Entity
class Directory(
    @EmbeddedId
    @AttributeOverride(name = "value", column = Column(name = "id", nullable = false))
    val id: DirectoryId,

    @Embedded
    @AttributeOverride(name = "value", column = Column(name = "userId", nullable = false))
    val userId: UserId,

    param: DirectoryParam
    ) {
    @Column(length = 128, nullable = false)
    var name: String = param.name
        private set

    @Column(length = 512, nullable = false)
    var description: String = param.description
        private set

    @Column(nullable = false)
    var status: CommonState = CommonState.ACTIVE
        private set

    fun update(param: DirectoryParam) {
        name = param.name
        description = param.description
    }

    fun hide() {
        status = CommonState.INACTIVE
    }

    fun delete() {
        status = CommonState.DELETED
    }
}