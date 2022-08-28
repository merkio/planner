package space.geek.planner

import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PlannerApplicationTests {

    @Test
    fun contextLoads() {
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
            registry.add("spring.sql.init.mode") { "always" }

            // Liquibase properties
            registry.add("spring.liquibase.enabled") { "true" }
            registry.add("spring.liquibase.change-log") { "classpath:/db/migrations/changelog.yaml" }
        }
    }
}
