import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootWar

//---------------------------------------------------------------------------------
// PLUGINS
//---------------------------------------------------------------------------------

plugins {
	id("java")
	id("com.dorongold.task-tree") version "2.1.0"
	id("war")
	//TODO: temporarily disable
	// id("checkstyle")
	// id("pmd")
	// id("de.aaschmid.cpd") version "3.3"
	// id("com.github.spotbugs") version "5.0.4"
	// id("jacoco") // if this does not work, remove the id around the name
	kotlin("jvm") version "1.7.10"
	id("org.springframework.boot") version "2.5.3"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	application
	war
}

group = "org.kryptokrona.api"

// getting project properties (gradle.properties)
val sdkVersion: String by project

java {
	sourceCompatibility = JavaVersion.VERSION_17
	targetCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

tasks.getByName<BootWar>("bootWar") {
	enabled = false
}

tasks.getByName<War>("war") {
	duplicatesStrategy = DuplicatesStrategy.INCLUDE
	enabled = true
}

tasks.withType<Jar> {
	duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

tasks.processResources {}

sourceSets {
	main {
		resources {
			srcDirs ("src/main/resources")
		}
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.liquibase:liquibase-core")
	implementation("org.springdoc:springdoc-openapi-ui:1.6.11")
	implementation("org.webjars:swagger-ui")

	testImplementation(kotlin("stdlib-jdk8"))
	testImplementation(kotlin("test"))

	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("org.postgresql:postgresql")
	providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

//---------------------------------------------------------------------------------
// TEST CONFIGURATION
//---------------------------------------------------------------------------------

tasks.withType<Test> {
	useJUnitPlatform()
	failFast = true // stop early to avoid running whole test suit if one fails
	// finalizedBy("jacocoTestReport") // report is always generated after tests run
}

/*tasks.jacocoTestReport {
    reports {
        xml.isEnabled = false
        csv.isEnabled = false
        html.isEnabled = true
        html.destination = file("$buildDir/kryptokrona-sdk-report")
    }

    finalizedBy("jacocoTestCoverageVerification")
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                //TODO this is set to 0.0 since we do not have implemented proper
                // unit tests yet to cover all code. Set to 0.7 when done.
                minimum = "0.0".toBigDecimal()
            }
        }
    }
}*/

//---------------------------------------------------------------------------------
// STATIC CODE ANALYSIS CONFIGURATION
//---------------------------------------------------------------------------------

/*checkstyle {
    config = rootProject.resources.text.fromFile("${projectDir}/gradle/static-code-analysis/checkstyle/checkstyle.xml")
    toolVersion = "8.12"
    isIgnoreFailures = false
}

pmd {
    toolVersion = "6.7.0"
    isIgnoreFailures = false
    ruleSetFiles = files("${projectDir}/gradle/static-code-analysis/pmd/ruleset.xml")
    ruleSets = listOf()
    rulesMinimumPriority.set(3)
}

cpd {
    language = "java"
    toolVersion = "6.1.0"
    minimumTokenCount = 200 // approximately 5-10 lines
}

tasks.named<de.aaschmid.gradle.plugins.cpd.Cpd>("cpdCheck") {
    ignoreFailures = true
    minimumTokenCount = 25
    setSource(files(
        // only check java source code
        subprojects.flatMap { it.the<SourceSetContainer>()["main"].java.srcDirs },
        subprojects.flatMap { it.the<SourceSetContainer>()["test"].java.srcDirs }
    ))
}

tasks.spotbugsMain {
    reports.create("html") {
        required.set(true)
        outputLocation.set(file("$projectDir/build/reports/spotbugs/main/spotbugs.html"))
        setStylesheet("fancy-hist.xsl")
    }
    excludeFilter.set(rootProject.file("${projectDir}/gradle/static-code-analysis/spotbugs/spotbugs-exclude.xml"))
    reports.maybeCreate("xml").isEnabled = false
    reports.maybeCreate("html").isEnabled = true
    maxHeapSize.set("256m")
}*/


//---------------------------------------------------------------------------------
// TASKS
//---------------------------------------------------------------------------------

tasks.register("printVersion") {
	doLast {
		println(project.version)
	}
}

tasks.register<Copy>("copyJavaDoc") {
	dependsOn("javadoc")
	println("${projectDir}/docs/${sdkVersion}")
	mkdir("${projectDir}/docs/${sdkVersion}")

	from("${buildDir}/docs/javadoc")
	into(layout.buildDirectory.dir("${projectDir}/docs/${sdkVersion}"))
	include("**/*.*")
}

tasks.withType<KotlinCompile> {
	kotlinOptions.jvmTarget = "17"
}
