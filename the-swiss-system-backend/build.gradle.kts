import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    kotlin("jvm") version "1.3.30"
    id("org.jetbrains.kotlin.plugin.noarg") version "1.3.30"
    id("org.jetbrains.kotlin.plugin.spring") version "1.3.30"
    id("org.jetbrains.kotlin.plugin.jpa") version "1.3.30"
    id("org.springframework.boot") version "2.1.4.RELEASE"
}

apply(plugin = "io.spring.dependency-management")

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib"))

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

sourceSets.create("integrationTest") {
    withConvention(KotlinSourceSet::class) {
        compileClasspath += sourceSets["main"].output + sourceSets["test"].output
        runtimeClasspath += sourceSets["main"].output + sourceSets["test"].output
        kotlin.srcDir("src/integrationTest/kotlin")
        resources.srcDir("src/integrationTest/resources")
    }
}

configurations["integrationTestCompile"].extendsFrom(configurations["testCompile"])
configurations["integrationTestRuntime"].extendsFrom(configurations["testRuntime"])

tasks {
    named<KotlinCompile>("compileKotlin") {
        kotlinOptions.jvmTarget = "1.8"
    }

    named<KotlinCompile>("compileTestKotlin") {
        kotlinOptions.jvmTarget = "1.8"
    }

    named<BootJar>("bootJar") {
        mainClassName = "be.fkunnen.theswisssystem.TheSwissSystemApplication"
        baseName = "the-swiss-system"
    }

    register<Test>("integrationTests") {
        description = "Runs the integration tests"
        group = "verification"
        dependsOn("composeUp")
        finalizedBy("composeDown")
        testClassesDirs = sourceSets["integrationTest"].output.classesDirs
        classpath = sourceSets["integrationTest"].runtimeClasspath
    }
    
}
