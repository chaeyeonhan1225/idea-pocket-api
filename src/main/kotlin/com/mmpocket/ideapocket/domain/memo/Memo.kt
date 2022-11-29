package com.mmpocket.ideapocket.domain.memo

import com.mmpocket.ideapocket.domain.CommonState
import com.mmpocket.ideapocket.domain.user.UserId
import javax.persistence.*

@Entity
class Memo(
    @EmbeddedId
    @AttributeOverride(name = "value", column = Column(name = "id", nullable = false))
    val id: MemoId,

    @Embedded
    @AttributeOverride(name = "value", column = Column(name = "userId", nullable = false))
    val userId: UserId,

    @Embedded
    @AttributeOverride(name = "value", column = Column(name = "directoryId", nullable = true))
    var directoryId: DirectoryId? = null,

    param: MemoParam
) {
    @Column(length = 128, nullable = false)
    var title: String = param.title

    @Column(length = 2048, nullable = false)
    var content: String = param.content

    @Version
    var version: Int = 0


        private set

    @Column(nullable = false)
    var status: CommonState = CommonState.ACTIVE

    fun update(param: MemoParam) {
        title = param.title
        content = param.content
    }

    fun updateDirectoryId(newDirectoryId: DirectoryId?) {
        directoryId = newDirectoryId
    }

    fun delete() {
        status = CommonState.DELETED
    }
}