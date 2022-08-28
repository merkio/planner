package space.geek.planner.domain.user

interface UserRepositoryAdapter {

    fun createUser(command: UserCreationCommand): User
}
