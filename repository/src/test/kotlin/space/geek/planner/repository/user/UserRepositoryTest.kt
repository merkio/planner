package space.geek.planner.repository.user

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import space.geek.planner.domain.user.UserCreationCommand
import space.geek.planner.domain.user.UserRepositoryAdapter
import space.geek.planner.repository.RepositoryTestBase

internal class UserRepositoryTest : RepositoryTestBase() {

    @Autowired
    private lateinit var userRepositoryAdapter: UserRepositoryAdapter

    @Test
    fun `create new user`() {
        val command = UserCreationCommand(
            email = "user@example.com",
            password = "pass"
        )

        val savedUser = userRepositoryAdapter.createUser(command)

        assertEquals(command.email, savedUser.email)
        assertEquals(command.password, savedUser.password)
    }
}
