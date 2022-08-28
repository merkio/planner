package space.geek.planner.repository.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import space.geek.planner.domain.user.User
import space.geek.planner.domain.user.UserCreationCommand
import space.geek.planner.domain.user.UserRepositoryAdapter

interface UserRepository : JpaRepository<UserEntity, Int>

@Component
internal class UserRepositoryAdapterImpl(
    private val userRepository: UserRepository
) : UserRepositoryAdapter {

    override fun createUser(command: UserCreationCommand): User =
        userRepository.save(UserEntity.from(command)).to()
}
