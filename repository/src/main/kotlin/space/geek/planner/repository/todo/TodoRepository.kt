package space.geek.planner.repository.todo

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import space.geek.planner.domain.todo.TodoRepositoryAdapter

interface TodoRepository : JpaRepository<TodoEntity, Int>

@Component
internal class TodoRepositoryAdapterImpl(
    private val todoRepository: TodoRepository
) : TodoRepositoryAdapter
