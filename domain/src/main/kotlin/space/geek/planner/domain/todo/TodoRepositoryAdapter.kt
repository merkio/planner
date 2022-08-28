package space.geek.planner.domain.todo

interface TodoRepositoryAdapter {
    fun createTodo(command: TodoCreationCommand): Todo
}
