import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("fabric-loom")
	kotlin("jvm") version "1.4.21"
}

repositories {
	maven("https://maven.fabricmc.net/")
}

dependencies {
	minecraft("com.mojang:minecraft:${project.property("minecraft")}")
	mappings("net.fabricmc:yarn:${project.property("yarn")}:v2")
	modImplementation("net.fabricmc:fabric-loader:${project.property("loader")}")
	modImplementation("net.fabricmc.fabric-api:fabric-api:${project.property("fabric_api")}")
	modImplementation("net.fabricmc:fabric-language-kotlin:${project.property("fabric_kotlin")}")
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

tasks.withType<JavaCompile> {
	options.encoding = "UTF-8"
}

tasks.withType<KotlinCompile> {
	kotlinOptions.jvmTarget = "1.8"
}