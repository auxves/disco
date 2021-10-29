pluginManagement {
	repositories {
		gradlePluginPortal()
		maven("https://maven.fabricmc.net")
	}

	plugins {
		id("fabric-loom") version "0.9-SNAPSHOT"
		kotlin("jvm") version "1.5.31"
	}
}
