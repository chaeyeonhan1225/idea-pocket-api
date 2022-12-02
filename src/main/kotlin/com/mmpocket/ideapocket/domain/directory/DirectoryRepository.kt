package com.mmpocket.ideapocket.domain.directory

import com.mmpocket.ideapocket.domain.memo.DirectoryId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface DirectoryRepository : JpaRepository<Directory, DirectoryId>, JpaSpecificationExecutor<Directory>