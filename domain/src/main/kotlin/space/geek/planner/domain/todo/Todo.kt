package space.geek.planner.domain.todo

import java.time.LocalDateTime

data class Todo(
    val name: String,
    val priority: Int,
    val status: TodoStatus,
    val userId: Long,
    val startTime: LocalDateTime = LocalDateTime.now(),
    val finishTime: LocalDateTime
)

enum class TodoStatus {
    IN_PROGRESS,
    FINISHED,
    BACKLOG,
    POSTPONED
}
