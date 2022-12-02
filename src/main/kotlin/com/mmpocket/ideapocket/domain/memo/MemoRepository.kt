package com.mmpocket.ideapocket.domain.memo

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface MemoRepository: JpaRepository<Memo, MemoId>, JpaSpecificationExecutor<Memo>