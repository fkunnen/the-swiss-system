import org.jetbrains.kotlin.com.google.common.collect.Lists

plugins {
	kotlin("jvm") version "1.3.30"
	id("org.jetbrains.kotlin.plugin.spring") version "1.3.30"
	id("org.springframework.boot")  version "2.1.4.RELEASE"
}

apply(plugin = "io.spring.dependency-management")

java {
	sourceCompatibility = JavaVersion.VERSION_1_8
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation(kotlin("reflect"))
	implementation(kotlin("stdlib-jdk8"))

	testImplementation("org.springframework.boot:spring-boot-starter-test")
}


tasks {
	compileKotlin {
		kotlinOptions {
			freeCompilerArgs = Lists.newArrayList("-Xjsr305=strict")
			jvmTarget = "1.8"
		}
	}

	compileTestKotlin {
		kotlinOptions {
			freeCompilerArgs = Lists.newArrayList("-Xjsr305=strict")
			jvmTarget = "1.8"
		}
	}

	bootJar {
		mainClassName = "be.fkunnen.theswisssystem.TheSwissSystemApplication"
	}

	clean {
		delete("$rootDir/out")
	}

}
