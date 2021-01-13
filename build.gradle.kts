import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.modrinth.minotaur.TaskModrinthUpload

java.sourceCompatibility = JavaVersion.VERSION_1_8
java.targetCompatibility = JavaVersion.VERSION_1_8

fun getArtifactPath(): String {
	return tasks.named<Jar>("jar").get().archiveFile.get().toString()
}

fun p(name: String): String {
	return project.property(name).toString()
}

plugins {
	id("fabric-loom")
	id("com.modrinth.minotaur") version "1.1.0"
	kotlin("jvm") version "1.4.21"
}

repositories {
	maven("https://maven.fabricmc.net/")
}

dependencies {
	minecraft("com.mojang:minecraft:${p("minecraft.target")}")
	mappings("net.fabricmc:yarn:${p("yarn")}:v2")
	modImplementation("net.fabricmc:fabric-loader:${p("loader")}")
	modImplementation("net.fabricmc.fabric-api:fabric-api:${p("fabric_api")}")
	modImplementation("net.fabricmc:fabric-language-kotlin:${p("fabric_kotlin")}")
}

tasks.named<Copy>("processResources") {
	duplicatesStrategy = DuplicatesStrategy.INCLUDE

	from(sourceSets["main"].resources.srcDirs) {
		include("fabric.mod.json")
		expand("version" to project.version)
	}

	from(sourceSets["main"].resources.srcDirs) {
		exclude("fabric.mod.json")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Jar> {
	from("LICENSE")
}

task<TaskModrinthUpload>("modrinth") {
	token = System.getenv("MODRINTH_TOKEN")
	projectId = p("id")

	versionNumber = p("version")
	versionName = "${project.property("name")} v$versionNumber"

	uploadFile = getArtifactPath()

	addGameVersion(p("minecraft.target"))

	p("minecraft.compatible").split(", ").forEach { addGameVersion(it) }

	addLoader("fabric")
}

task("publish") {
	dependsOn("build")
	dependsOn("modrinth")
}