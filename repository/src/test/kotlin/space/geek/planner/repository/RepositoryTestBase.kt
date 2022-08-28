package space.geek.planner.repository

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import space.geek.planner.repository.todo.TodoRepository
import space.geek.planner.repository.user.UserRepository

@SpringBootTest
@Testcontainers
internal class RepositoryTestBase {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var todoRepository: TodoRepository

    @BeforeEach
    fun setup() {
        userRepository.deleteAll()
        todoRepository.deleteAll()
    }

    @AfterEach
    fun tearDown() {
    }

    companion object {

        @Container
        val container = PostgreSQLContainer("postgres:14").apply {
            withReuse(true)
            withDatabaseName("testdb")
            withUsername("test")
            withPassword("test")
        }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            // JPA repositories
            registry.add("spring.datasource.url", container::getJdbcUrl)
            registry.add("spring.datasource.username", container::getUsername)
            registry.add("spring.datasource.password", container::getPassword)

            // Liquibase properties
            registry.add("spring.liquibase.enabled") { "true" }
            registry.add("spring.liquibase.change-log") { "classpath:/db/migrations/changelog.yaml" }
        }
    }
}

@EnableJpaAuditing
@SpringBootApplication
internal class TestApplication
