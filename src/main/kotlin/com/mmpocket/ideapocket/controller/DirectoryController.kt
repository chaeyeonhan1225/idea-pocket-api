package com.mmpocket.ideapocket.controller

import com.mmpocket.ideapocket.application.DirectoryApplication
import com.mmpocket.ideapocket.application.DirectoryProvider
import com.mmpocket.ideapocket.application.UserProvider
import com.mmpocket.ideapocket.domain.PagedList
import com.mmpocket.ideapocket.domain.directory.Directory
import com.mmpocket.ideapocket.domain.directory.DirectoryParam
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("v1/directories")
class DirectoryController(
    private val provider: DirectoryProvider,
    private val userProvider: UserProvider,
    private val service: DirectoryApplication
) {
    @GetMapping
    fun findAll(principal: Principal, @RequestParam page: Int?, @RequestParam size: Int?): PagedList<Directory> {
        val userId = findUserIdByUsername(principal.name)
        return provider.findAll(userId = userId, page = page, size = size)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String): Directory {
        return provider.findById(id)
    }

    @PostMapping
    fun createDirectory(@RequestBody param: DirectoryParam): Directory {
        return service.createDirectory(param)
    }

    @PutMapping("/{id}")
    fun updateMemo(@PathVariable id: String, @RequestBody param: DirectoryParam): Directory {
        return service.updateDirectory(id, param)
    }

    @DeleteMapping("/{id}")
    fun deleteMemo(@PathVariable id: String): Boolean {
        return service.deleteDirectory(id)
    }

    private fun findUserIdByUsername(username: String): String {
        val user = userProvider.findByEmail(username)
        return user.id.value.toString()
    }
}