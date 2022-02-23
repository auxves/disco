pluginManagement {
	repositories {
		gradlePluginPortal()
		maven("https://maven.fabricmc.net")
	}

	plugins {
		id("fabric-loom") version "0.11-SNAPSHOT"
		kotlin("jvm") version "1.6.10"
	}
}
