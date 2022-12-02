package com.mmpocket.ideapocket.controller

import com.mmpocket.ideapocket.application.MemoApplication
import com.mmpocket.ideapocket.application.MemoProvider
import com.mmpocket.ideapocket.application.UserProvider
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
import java.security.Principal

@RestController
@RequestMapping("v1/memos")
class MemoController(
    private val provider: MemoProvider,
    private val userProvider: UserProvider,
    private val service: MemoApplication
) {
    @GetMapping
    fun findMemos(principal: Principal): List<Memo> {
        val userId = findUserIdByUsername(principal.name)
        return provider.findAll(userId = userId)
    }

    @GetMapping("/{id}")
    fun findMemoById(@PathVariable id: String): Memo {
        return provider.findById(id)
    }

    @PostMapping
    fun createMemo(@RequestBody param: MemoParam, principal: Principal): Memo {
        val userId = findUserIdByUsername(principal.name)
        return service.createMemo(param = param, userId = userId)
    }

    @PutMapping("/{id}")
    fun updateMemo(@PathVariable id: String, @RequestBody param: MemoParam): Memo {
        return service.updateMemo(id, param)
    }

    @DeleteMapping("/{id}")
    fun deleteMemo(@PathVariable id: String): Boolean {
        return service.deleteMemo(id)
    }

    private fun findUserIdByUsername(username: String): String {
        val user = userProvider.findByEmail(username)
        return user.id.value.toString()
    }
}