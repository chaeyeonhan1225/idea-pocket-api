package com.mmpocket.ideapocket.controller

import com.mmpocket.ideapocket.application.MemoApplication
import com.mmpocket.ideapocket.application.MemoProvider
import com.mmpocket.ideapocket.domain.memo.Memo
import com.mmpocket.ideapocket.domain.memo.MemoParam
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("memos")
class MemoController(
    private val provider: MemoProvider,
    private val service: MemoApplication
) {
    @GetMapping
    fun findMemos(): List<Memo> {
        return provider.findAll()
    }

    @GetMapping("/{id}")
    fun findMemoById(@PathVariable id: String) {
        // TODO: findById 구현
    }

    @PostMapping
    fun createMemo(@RequestBody param: MemoParam): Memo {
        return service.createMemo(param = param)
    }

    @PutMapping("/{id}")
    fun updateMemo(@PathVariable id: String, @RequestBody param: MemoParam): Memo {
        return service.updateMemo(id, param)
    }

    @DeleteMapping("/{id}")
    fun deleteMemo(@PathVariable id: String): Boolean {
        return service.deleteMemo(id)
    }
}