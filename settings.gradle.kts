pluginManagement {
	repositories {
		gradlePluginPortal()
		maven("https://maven.fabricmc.net")
	}

	plugins {
		id("fabric-loom") version "0.5-SNAPSHOT"
		kotlin("jvm") version embeddedKotlinVersion
	}
}
