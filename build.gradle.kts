import com.avast.gradle.dockercompose.tasks.ComposeBuild
import com.avast.gradle.dockercompose.tasks.ComposeUp

plugins {
    id("com.avast.gradle.docker-compose") version "0.9.4"
}

dockerCompose.createNested("database").apply {
    startedServices = listOf("mysql")
}

tasks {
    named<ComposeBuild>("composeBuild") {
        dependsOn(":the-swiss-system-backend:build")
    }

    named<ComposeUp>("composeUp") {
        dependsOn("composeBuild")
    }
}