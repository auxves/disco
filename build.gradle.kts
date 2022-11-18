import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

java.sourceCompatibility = JavaVersion.VERSION_1_8
java.targetCompatibility = JavaVersion.VERSION_1_8

fun p(name: String) = project.property(name).toString()

plugins {
	id("fabric-loom")
	kotlin("jvm")
}

dependencies {
	minecraft("com.mojang:minecraft:${p("minecraft")}")
	mappings("net.fabricmc:yarn:${p("yarn")}:v2")

	modImplementation("net.fabricmc:fabric-loader:${p("loader")}")
	modImplementation("net.fabricmc.fabric-api:fabric-api:${p("fabric_api")}")
	modImplementation("net.fabricmc:fabric-language-kotlin:${p("fabric_kotlin")}")
}

tasks.named<Copy>("processResources") {
	duplicatesStrategy = DuplicatesStrategy.INCLUDE

	from(sourceSets["main"].resources.srcDirs) {
		include("fabric.mod.json")
		expand(project.properties)
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
