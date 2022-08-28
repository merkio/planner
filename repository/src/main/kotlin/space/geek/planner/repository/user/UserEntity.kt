package space.geek.planner.repository.user

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import space.geek.planner.domain.user.User
import space.geek.planner.domain.user.UserCreationCommand
import space.geek.planner.domain.user.UserStatus
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.EntityListeners
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "user", schema = "planner")
@EntityListeners(AuditingEntityListener::class)
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val email: String,
    val password: String,
    val enabled: Boolean,
    @field:Enumerated(EnumType.STRING)
    val status: UserStatus,
    @CreationTimestamp
    val createdAt: LocalDateTime? = null,
    @UpdateTimestamp
    val updatedAt: LocalDateTime? = null
) {

    fun to(): User =
        User(
            id = id!!,
            email = email,
            password = password,
            status = status,
            enabled = enabled
        )

    companion object {
        fun from(command: UserCreationCommand): UserEntity =
            with(command) {
                UserEntity(
                    email = email,
                    password = password,
                    enabled = enabled,
                    status = status
                )
            }
    }
}
