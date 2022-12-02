package com.mmpocket.ideapocket.domain.directory

import com.mmpocket.ideapocket.domain.CommonState
import com.mmpocket.ideapocket.domain.user.UserId
import org.springframework.data.jpa.domain.Specification

class DirectorySpecification(
    private val filter: DirectoryFilter
) {
    companion object {
        fun byUserId(userId: String) = Specification<Directory> { root, _, cb ->
            cb.equal(root.get<UserId>("userId"), UserId(userId.toLong()))
        }
    }

    fun build(): Specification<Directory> {
        var spec = Specification<Directory> { root, _, criteriaBuilder ->
            criteriaBuilder.notEqual(
                root.get<CommonState>("status"),
                CommonState.DELETED
            )
        }
        filter?.run {
            this.userId?.let {
                spec = spec.and(byUserId(userId))
            }
        }
        return spec
    }
}

data class DirectoryFilter(
    val userId: String?
)