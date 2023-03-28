import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import java.util.*

val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val postgres_version: String by project
val h2_version: String by project
val ktorm_version: String by project
val liquibase_core: String by project
val ktorm_jackson_version: String by project
val slf4j_version: String by project

plugins {
    application
    kotlin("jvm") version "1.8.10"
    id("io.ktor.plugin") version "2.2.3"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.10"
    id("java")
    id("org.liquibase.gradle") version "2.1.1"
}

group = "org.kryptokrona.api"
version = "0.1.0"

application {
    mainClass.set("org.kryptokrona.api.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

ktor {
    docker {
        jreVersion.set(io.ktor.plugin.features.JreVersion.JRE_17)
    }
}

repositories {
    mavenCentral()
    // used for getting snapshots of kryptokrona kotlin sdk
    // maven("https://s01.oss.sonatype.org/content/repositories/snapshots")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().all {
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    // kryptokrona sdk
    implementation("org.kryptokrona.sdk:kryptokrona-core:0.1.1")
    implementation("org.kryptokrona.sdk:kryptokrona-http:0.1.1")
    implementation("org.kryptokrona.sdk:kryptokrona-util:0.1.1")

    // various
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")
    implementation("io.ktor:ktor-utils-jvm:$ktor_version")
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-servlet:$ktor_version")
    implementation("org.ktorm:ktorm-support-postgresql:$ktorm_version")
    implementation("org.postgresql:postgresql:$postgres_version")
    implementation("io.ktor:ktor-serialization-jackson-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-swagger:$ktor_version")
    implementation("org.ktorm:ktorm-core:$ktorm_version")
    implementation("io.ktor:ktor-server-config-yaml-jvm:2.2.3")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("org.ktorm:ktorm-jackson:$ktorm_jackson_version")
    implementation("org.slf4j:slf4j-api:$slf4j_version")
    implementation("org.slf4j:slf4j-simple:$slf4j_version")
    implementation("javax.xml.bind:jaxb-api:2.3.1")

    // database
    implementation("org.apache.commons:commons-dbcp2:2.9.0")

    // liquibase
    liquibaseRuntime("org.liquibase:liquibase-core:$liquibase_core")
    liquibaseRuntime("org.postgresql:postgresql:$postgres_version")
    liquibaseRuntime("info.picocli:picocli:4.6.3")
    liquibaseRuntime("ch.qos.logback:logback-core:1.2.3")
    liquibaseRuntime("ch.qos.logback:logback-classic:1.2.3")
    liquibaseRuntime("javax.xml.bind:jaxb-api:2.2.4")

    runtimeOnly("com.squareup:kotlinpoet:0.7.0")

    // testing
    testImplementation("io.ktor:ktor-server-test-host-jvm:2.2.3")
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}

tasks.jar {
    archiveFileName.set("kryptokrona-api.jar")
    manifest {
        attributes["Main-Class"] = "org.kryptokrona.api.ApplicationKt"
    }
    // exclude("META-INF/*.RSA", "META-INF/*.SF", "META-INF/*.DSA")
}

tasks.withType<ShadowJar> {
    manifest {
        attributes["Main-Class"] = "org.kryptokrona.api.ApplicationKt"
    }
    archiveFileName.set("kryptokrona-api-shadow.jar")
    from(sourceSets["main"].output)
    configurations = listOf(project.configurations["compileClasspath"])
}


// database migrations
val dbEnv: String by project.extra

val propertiesFile = file("local.properties")
val properties = Properties()
if (propertiesFile.exists()) {
    properties.load(propertiesFile.inputStream())
    println(properties.getProperty("liquibase.dev.url"))
}

liquibase {
    activities.register("dev") {
        val url = properties.getProperty("liquibase.dev.url") ?: System.getenv("LIQUIBASE_DEV_URL")
        val user = properties.getProperty("liquibase.dev.user") ?: System.getenv("LIQUIBASE_DEV_USER")
        val password = properties.getProperty("liquibase.dev.password") ?: System.getenv("LIQUIBASE_DEV_PASSWORD")

        this.arguments = mapOf(
            "logLevel" to "info",
            "changeLogFile" to "src/main/resources/db/changelog/master.xml",
            "url" to url,
            "username" to user,
            "password" to password,
            "classpath" to "src/main/resources/"
        )
    }

    activities.register("prod") {
        val url = System.getenv("LIQUIBASE_URL")
        val user = System.getenv("LIQUIBASE_USER")
        val password = System.getenv("LIQUIBASE_PASSWORD")

        this.arguments = mapOf(
            "logLevel" to "info",
            "changeLogFile" to "src/main/resources/db/changelog/master.xml",
            "url" to url,
            "username" to user,
            "password" to password,
            "classpath" to "src/main/resources/"
        )
    }

    runList = dbEnv
}