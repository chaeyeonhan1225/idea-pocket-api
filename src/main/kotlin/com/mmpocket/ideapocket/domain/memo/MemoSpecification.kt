package com.mmpocket.ideapocket.domain.memo

import com.mmpocket.ideapocket.domain.CommonState
import com.mmpocket.ideapocket.domain.user.UserId
import org.springframework.data.jpa.domain.Specification

class MemoSpecification(
    private val filter: MemoFilter?
) {
    companion object {
        fun byUserId(userId: String) = Specification<Memo> { root, query, criteriaBuilder ->
            criteriaBuilder.equal(root.get<UserId>("userId"), UserId(userId.toLong()))
        }
    }

    fun build(): Specification<Memo> {
        var spec = Specification<Memo> { root, query, criteriaBuilder ->
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

data class MemoFilter(
    val userId: String?
)