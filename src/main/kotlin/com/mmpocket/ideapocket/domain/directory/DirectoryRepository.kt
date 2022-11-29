package com.mmpocket.ideapocket.domain.directory

import com.mmpocket.ideapocket.domain.memo.DirectoryId
import org.springframework.data.jpa.repository.JpaRepository

interface DirectoryRepository: JpaRepository<Directory, DirectoryId>