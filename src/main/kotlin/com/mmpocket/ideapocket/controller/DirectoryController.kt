package com.mmpocket.ideapocket.controller

import com.mmpocket.ideapocket.application.DirectoryApplication
import com.mmpocket.ideapocket.application.DirectoryProvider
import com.mmpocket.ideapocket.domain.directory.Directory
import com.mmpocket.ideapocket.domain.directory.DirectoryParam
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v1/directories")
class DirectoryController(
    private val provider: DirectoryProvider,
    private val service: DirectoryApplication
) {
    @GetMapping
    fun findAll(): List<Directory> {
        return provider.findAll()
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

}