package com.mmpocket.ideapocket.domain.user

import com.mmpocket.ideapocket.domain.CommonState
import org.hibernate.annotations.Where
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity
@Table(name = "MemoUser")
@Where(clause = "status > 0")
class User(
    @EmbeddedId
    @AttributeOverride(name = "value", column = Column(name = "id", nullable = false))
    val id: UserId,

    param: UserParam
): UserDetails {
    @Column(length = 36, nullable = false)
    var nickname: String = param.nickname

    @Column(length = 72, nullable = false)
    var email: String = param.email

    @Column(length = 256, nullable = false, name = "password")
    private var memberPassword: String = param.password

    @Column(nullable = false)
    var status: CommonState = CommonState.ACTIVE

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf()
    }

    override fun getPassword(): String = memberPassword

    // 로그인할때 email을 사용하기 때문에 nickname이 아닌 email을 리턴해야함
    override fun getUsername(): String = email

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}