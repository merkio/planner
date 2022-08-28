package space.geek.planner.archtests

import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.library.Architectures

@AnalyzeClasses(packages = ["space.geek.planner"])
internal class HexagonalArchitectureTest {

    @ArchTest
    val layeredArchitectureTest: Architectures.LayeredArchitecture = Architectures.layeredArchitecture()
        .consideringAllDependencies()
        .layer("API").definedBy("space.geek.planner.api..")
        .layer("Repository").definedBy("space.geek.planner.repository..")
        .layer("Domain").definedBy("space.geek.planner.domain..")
        .layer("Application").definedBy("space.geek.planner.application..")
        .whereLayer("API").mayNotBeAccessedByAnyLayer()
        .whereLayer("Repository").mayNotBeAccessedByAnyLayer()
        .whereLayer("Application").mayOnlyBeAccessedByLayers("Messaging", "API")
        .whereLayer("Domain").mayOnlyBeAccessedByLayers(
            "API",
            "Application",
            "Repository"
        )
}
