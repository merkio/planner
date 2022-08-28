package space.geek.planner.repository.todo

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import space.geek.planner.domain.todo.TodoCreationCommand
import space.geek.planner.domain.todo.TodoRepositoryAdapter
import space.geek.planner.repository.RepositoryTestBase
import kotlin.test.assertEquals

internal class TodoRepositoryTest : RepositoryTestBase() {

    @Autowired
    private lateinit var todoRepositoryAdapter: TodoRepositoryAdapter

    @Test
    fun `create todo`() {
        val command = TodoCreationCommand(
            name = "Todo",
            priority = 0
        )

        val savedTodo = todoRepositoryAdapter.createTodo(command)

        assertEquals(command.name, savedTodo.name)
        assertEquals(command.priority, savedTodo.priority)
    }
}
