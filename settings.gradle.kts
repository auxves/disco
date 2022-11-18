pluginManagement {
	repositories {
		gradlePluginPortal()
		maven("https://maven.fabricmc.net")
	}

	plugins {
		id("fabric-loom") version "1.0-SNAPSHOT"
		kotlin("jvm") version "1.7.21"
	}
}
