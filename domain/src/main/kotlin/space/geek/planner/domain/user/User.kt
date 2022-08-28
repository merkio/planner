package space.geek.planner.domain.user

data class User(
    val id: Long,
    val email: String,
    val password: String,
    val status: UserStatus = UserStatus.ACTIVE,
    val enabled: Boolean = true
)

enum class UserStatus {
    ACTIVE,
    INACTIVE
}

data class UserCreationCommand(
    val email: String,
    val password: String,
    val status: UserStatus = UserStatus.ACTIVE,
    val enabled: Boolean = true
)
