pluginManagement {
	repositories {
		gradlePluginPortal()
		maven("https://maven.fabricmc.net")
	}

	plugins {
		id("fabric-loom") version "1.3-SNAPSHOT"
		kotlin("jvm") version "1.9.10"
	}
}
