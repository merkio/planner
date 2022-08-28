package space.geek.planner.domain.todo

import java.time.LocalDateTime

data class Todo(
    val id: Long,
    val name: String,
    val priority: Int,
    val status: TodoStatus,
    val userId: Long,
    val startTime: LocalDateTime = LocalDateTime.now(),
    val finishTime: LocalDateTime? = null
)

enum class TodoStatus {
    IN_PROGRESS,
    FINISHED,
    BACKLOG,
    POSTPONED
}

data class TodoCreationCommand(
    val name: String,
    val priority: Int,
    val status: TodoStatus = TodoStatus.BACKLOG,
    val userId: Long = 1,
    val startTime: LocalDateTime = LocalDateTime.now(),
    val finishTime: LocalDateTime? = null
)
